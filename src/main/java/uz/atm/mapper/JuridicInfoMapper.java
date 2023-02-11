package uz.atm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.atm.dto.JuridicInfoUpdateDto;
import uz.atm.entity.juridicInfo.JuridicInfoUpdate;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:47 PM
 **/
@Component
@Mapper( componentModel = "spring" )
public interface JuridicInfoMapper {

    @Mapping( target = "date", ignore = true )
    JuridicInfoUpdate fromUpdateDto(JuridicInfoUpdateDto dto);

}
