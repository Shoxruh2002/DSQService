package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.queueLog.JuridicMethodRequest;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 5:01 PM
 **/
public interface JuridicMethodRequestRepository extends JpaRepository<JuridicMethodRequest, Long> {
}
