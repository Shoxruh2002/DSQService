package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.queueLog.JuridicMethodResponse;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 5:02 PM
 **/
public interface JuridicMethodResponseRepository extends JpaRepository<JuridicMethodResponse, Long> {
}
