package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.juridicInfo.faunders.Founders;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 12:34 PM
 **/
public interface FoundersRepository extends JpaRepository<Founders, Long> {
}
