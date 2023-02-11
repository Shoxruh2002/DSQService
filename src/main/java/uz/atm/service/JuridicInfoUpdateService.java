package uz.atm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm.dto.JuridicInfoUpdateDto;
import uz.atm.entity.juridicInfo.JuridicInfoUpdate;
import uz.atm.mapper.JuridicInfoMapper;
import uz.atm.repository.JuridicInfoUpdateRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:46 PM
 **/
@Slf4j
@Service
public class JuridicInfoUpdateService extends AbstractService<JuridicInfoUpdateRepository> {

    private final JuridicInfoMapper juridicInfoMapper;
    private final JuridicInfoService juridicInfoService;

    public JuridicInfoUpdateService(JuridicInfoUpdateRepository repository, JuridicInfoMapper juridicInfoMapper, JuridicInfoService juridicInfoService) {
        super(repository);
        this.juridicInfoMapper = juridicInfoMapper;
        this.juridicInfoService = juridicInfoService;
    }

    public JuridicInfoUpdate save(JuridicInfoUpdateDto dto) {
        JuridicInfoUpdate juridicInfoUpdate = juridicInfoMapper.fromUpdateDto(dto);
        try {
            juridicInfoUpdate.setDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(dto.getDate()));
        } catch ( ParseException e ) {
            log.error("Error occurred while parsing string to date  with dto : {}", dto);
        }
        JuridicInfoUpdate save = repository.save(juridicInfoUpdate);
        this.update(dto, save);
        return save;
    }

    public void update(JuridicInfoUpdateDto dto, JuridicInfoUpdate save) {
        Thread thread = new Thread(() -> {
            if ( juridicInfoService.updateFromDsq(dto) )
                this.update(save.getId(), true);
        });
        thread.start();
    }

    private void update(Long id, boolean updated) {
        repository.updateById(id, updated);
    }


}
