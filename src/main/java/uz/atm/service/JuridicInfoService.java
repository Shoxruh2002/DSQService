package uz.atm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.webjars.NotFoundException;
import uz.atm.dto.AppErrorDto;
import uz.atm.dto.DataDto;
import uz.atm.dto.JuridicInfoUpdateDto;
import uz.atm.dto.etp.JuridicInfoEtpRequest;
import uz.atm.dto.etp.JuridicInfoEtpResponse;
import uz.atm.entity.juridicInfo.JuridicInfo;
import uz.atm.entity.juridicInfo.faunders.Founders;
import uz.atm.entity.queueLog.JuridicMethodRequest;
import uz.atm.entity.queueLog.JuridicMethodResponse;
import uz.atm.properties.DSQApiProperties;
import uz.atm.repository.FoundersRepository;
import uz.atm.repository.JuridicInfoRepository;
import uz.atm.repository.JuridicMethodRequestRepository;
import uz.atm.repository.JuridicMethodResponseRepository;
import uz.atm.service.caller.DSQCaller;

import java.util.List;
import java.util.Optional;

import static uz.atm.utils.AppUtils.getRequestResponseIdFromFile;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/14/22 4:31 PM
 **/
@Slf4j
@Service
public class JuridicInfoService extends AbstractService<JuridicInfoRepository> {

    private final DSQCaller dsqCaller;
    private final DSQApiProperties dsqApiProperties;
    private final FoundersRepository foundersRepository;
    private final RabbitMqService rabbitMqService;
    private final JuridicMethodRequestRepository juridicMethodRequestRepository;
    private final JuridicMethodResponseRepository juridicMethodResponseRepository;
    @Value( "${request.id.file.path}" )
    private String getRequestFilePath;

    public JuridicInfoService(JuridicInfoRepository repository, DSQCaller dsqCaller, DSQApiProperties dsqApiProperties, FoundersRepository foundersRepository, RabbitMqService rabbitMqService, JuridicMethodRequestRepository juridicMethodRequestRepository, JuridicMethodResponseRepository juridicMethodResponseRepository) {
        super(repository);
        this.dsqCaller = dsqCaller;
        this.dsqApiProperties = dsqApiProperties;
        this.foundersRepository = foundersRepository;
        this.rabbitMqService = rabbitMqService;
        this.juridicMethodRequestRepository = juridicMethodRequestRepository;
        this.juridicMethodResponseRepository = juridicMethodResponseRepository;
    }


    public Optional<JuridicInfo> findByCompanyTin(Long tin) {
        return repository.findByCompany_Tin(tin);
    }

    public JuridicInfo save(JuridicInfo juridicInfo) {
        return repository.save(juridicInfo);
    }

    public JuridicInfo update(JuridicInfo juridicInfo) {
        Optional<JuridicInfo> byCompanyTin = repository.findByCompany_Tin(juridicInfo.getCompany().getTin());
        if ( byCompanyTin.isPresent() ) {
            juridicInfo.setId(byCompanyTin.get().getId());
            return repository.save(juridicInfo);
        }
        throw new NotFoundException(" While updating Juridic Info not found with tin : " + juridicInfo.getCompany().getTin());
    }


    public boolean updateFromDsq(JuridicInfoUpdateDto dto) {
        Long tin = dto.getData().getTin();
        String sendType = dto.getSendType();
        DataDto<JuridicInfo> juridicEntityInfo = this.getJuridicEntityInfo(tin);
        if ( juridicEntityInfo.success ) {
            this.update(juridicEntityInfo.body, sendType);
            return true;
        } else return false;
    }

    private void update(JuridicInfo dto, String sendType) {
        switch ( sendType ) {
            case "CREATE" -> this.save(dto);
            case "UPDATE" -> this.update(dto);
        }
    }


    public DataDto<JuridicInfo> getJuridicEntityInfo(Long tin) {
        if ( tin.toString().length() != 9 )
            return new DataDto<>(new AppErrorDto("Tin must be contains 9 numbers", HttpStatus.BAD_REQUEST));
        String endpoint = dsqApiProperties.getUrl().getPath().getJuridicInfoAPI();
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tin", tin.toString());
        DataDto<JuridicInfo> response = dsqCaller.getCall(params, endpoint, JuridicInfo.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.getCall(params, endpoint, JuridicInfo.class);
        }
        return response;
    }


    public DataDto<List<Founders>> getFounders(String tin) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tin", tin);// TODO: 2/9/23 Should optimize
        DataDto<List<Founders>> dataDto = dsqCaller.getCall(params, "minfin2/api/get/founders-by-tin", new ParameterizedTypeReference<>() {
        });
        if ( ! dataDto.success )
            dsqCaller.getCall(params, "minfin2/api/get/founders-by-tin", new ParameterizedTypeReference<>() {
            });
        foundersRepository.saveAll(dataDto.body);
        return dataDto;
    }

    public boolean sendJuridicInfoForEtp(JuridicInfoEtpRequest dto, String type) {
        boolean save = type.equals("prod");

        if ( save ) this.saveJuridicMethodRequest(dto);
        dto.getPayload().getTins().forEach(f -> {
            JuridicInfoEtpResponse etpResponse = new JuridicInfoEtpResponse(dto.getEtpId(), dto.getRequestId(), getRequestResponseIdFromFile(getRequestFilePath), dto.getMethodName());
            DataDto<JuridicInfo> juridicEntityInfo = this.getJuridicEntityInfo(f);
            DataDto<List<Founders>> founders = this.getFounders(String.valueOf(f));
            if ( founders.success && juridicEntityInfo.success )
                etpResponse.setPayload(new JuridicInfoEtpResponse.Payload("SUCCESS", null, juridicEntityInfo.body, founders.body));
            else {
                StringBuilder errMsg = new StringBuilder("Something went wrong with tin : ").append(f).append("; ");
                if ( ! founders.success ) errMsg.append("Cause 1 : ").append(founders.error.getMessage());
                if ( ! juridicEntityInfo.success )
                    errMsg.append("Cause 2 : ").append(juridicEntityInfo.error.getMessage());
                etpResponse.setPayload(new JuridicInfoEtpResponse.Payload("ERROR", errMsg.toString(), null, null));
                log.error("Error sent to Etp from Juridic Method : {}", etpResponse.toString());
            }
            rabbitMqService.sendEtp(etpResponse, type);
            if ( save ) this.saveJuridicMethodResponse(etpResponse);
        });
        return true;
    }

    public void saveJuridicMethodRequest(JuridicInfoEtpRequest dto) {
        juridicMethodRequestRepository.save(new JuridicMethodRequest(dto.getRequestId(), dto.getEtpId(), dto.getMethodName(), dto.getPayload().toString()));
    }

    public void saveJuridicMethodResponse(JuridicInfoEtpResponse dto) {
        juridicMethodResponseRepository.save(new JuridicMethodResponse(dto.getEtpId(), dto.getRequestId(), dto.getResponseId(), dto.getMethodName(), dto.getPayload().toString()));
    }


}
