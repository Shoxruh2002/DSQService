package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.organizationDebt.OrganizationDebtInfo;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 12:37 PM
 **/
public interface OrganizationDebtInfoRepository extends JpaRepository<OrganizationDebtInfo, Long> {

    Optional<OrganizationDebtInfo> findByTin(Long tin);
}
