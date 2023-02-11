package uz.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uz.atm.dto.AppErrorDto;
import uz.atm.dto.DataDto;
import uz.atm.dto.TaxGapDto;
import uz.atm.dto.etp.TaxGapRequest;
import uz.atm.dto.etp.TaxGapResponse;
import uz.atm.entity.tax.TaxGap;
import uz.atm.mapper.TaxGapMapper;
import uz.atm.properties.DSQApiProperties;
import uz.atm.repository.TaxGapRepository;
import uz.atm.service.caller.DSQCaller;

import javax.validation.constraints.NotNull;
import java.util.function.BiFunction;

import static uz.atm.utils.AppUtils.getRequestResponseIdFromFile;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 6:53 PM
 **/
@Service
@Slf4j
public class TaxGapService extends AbstractService<TaxGapRepository> {

    private final DSQCaller dsqCaller;
    private final ObjectMapper objectMapper;
    private final DSQApiProperties dsqApiProperties;
    private final TaxGapMapper taxGapMapper;
    private final RabbitMqService rabbitMqService;
    @Value( "${request.id.file.path}" )
    private String responseIdFilePath;


    public TaxGapService(TaxGapRepository repository, DSQCaller dsqCaller, ObjectMapper objectMapper, DSQApiProperties dsqApiProperties, TaxGapMapper taxGapMapper, RabbitMqService rabbitMqService) {
        super(repository);
        this.dsqCaller = dsqCaller;
        this.objectMapper = objectMapper;
        this.dsqApiProperties = dsqApiProperties;
        this.taxGapMapper = taxGapMapper;
        this.rabbitMqService = rabbitMqService;
    }

    public DataDto<TaxGapDto> getTaxGapFromDSQ(@NotNull Long tin) {
        String endpoint = dsqApiProperties.getUrl().getPath().getTaxGapInfoAPI();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tin", tin.toString());
        DataDto<JsonNode> response = dsqCaller.getCall(params, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.getCall(params, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                try {
                    return new DataDto<>(objectMapper.readValue(body.get("data").toString(), TaxGapDto.class));
                } catch ( JsonProcessingException e ) {
                    e.printStackTrace();
                    return new DataDto<>(new AppErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } else return new DataDto<>(new AppErrorDto(body.get("reason").asText(), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }

    public DataDto<TaxGapDto> sendTaxGapResponseForEtp(TaxGapRequest request, String type) {
        DataDto<TaxGapDto> taxGapFromDSQ = getTaxGapFromDSQ(request.getPayload().getTin());
        if ( taxGapFromDSQ.success ) {
            TaxGapDto taxGapDto = taxGapFromDSQ.body;
            TaxGap taxGap = taxGapMapper.fromDto(taxGapDto);
            taxGap.setEtpId(request.getEtpId());
            taxGap.setEtpRequestId(request.getRequestId());
            TaxGapResponse taxGapResponse = getRequestFromResponse.apply(request, taxGapDto);
            taxGap.setResponseId(taxGapResponse.getResponseId());
            if ( type.equals("prod") ) repository.save(taxGap);
            rabbitMqService.sendEtp(taxGapResponse, type);
        } else {
            TaxGapResponse errorDto = getErrorFromResponse.apply(request, taxGapFromDSQ.error);
            log.error("Error sent to fot tax_Method  Etp Cause :{}", errorDto.toString());
            rabbitMqService.sendEtp(errorDto, type);
        }
        return taxGapFromDSQ;
    }

    BiFunction<TaxGapRequest, TaxGapDto, TaxGapResponse> getRequestFromResponse = (request, dto) -> {
        TaxGapResponse taxGapResponse = new TaxGapResponse(request.getEtpId(), request.getRequestId(), getRequestResponseIdFromFile(responseIdFilePath), request.getMethodName());
        taxGapResponse.setPayload(new TaxGapResponse.Payload("SUCCESS", null, dto));
        return taxGapResponse;
    };

    BiFunction<TaxGapRequest, AppErrorDto, TaxGapResponse> getErrorFromResponse = (request, error) -> {
        TaxGapResponse taxGapResponse = new TaxGapResponse(request.getEtpId(), request.getRequestId(), getRequestResponseIdFromFile(responseIdFilePath), request.getMethodName());
        taxGapResponse.setPayload(new TaxGapResponse.Payload("ERROR", error.getMessage(), null));
        return taxGapResponse;
    };
}

