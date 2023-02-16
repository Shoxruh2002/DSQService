package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.atm.entity.juridicInfo.JuridicInfo;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/14/22 5:06 PM
 **/
public interface JuridicInfoRepository extends JpaRepository<JuridicInfo, Long> {

    Optional<JuridicInfo> findByCompany_Tin(Long company_tin);
}
