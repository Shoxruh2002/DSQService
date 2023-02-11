package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.tax.TaxGap;


/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:03 PM
 **/
public interface TaxGapRepository extends JpaRepository<TaxGap, Long> {
}
