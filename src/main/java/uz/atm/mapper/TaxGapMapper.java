package uz.atm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.atm.dto.TaxGapDto;
import uz.atm.entity.tax.TaxGap;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:44 PM
 **/
@Component
@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TaxGapMapper {

    TaxGap fromDto(TaxGapDto dto);

}
