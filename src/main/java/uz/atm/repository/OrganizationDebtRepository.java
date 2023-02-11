package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.atm.entity.organizationDebt.OrganizationDebt;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 12:36 PM
 **/
public interface OrganizationDebtRepository extends JpaRepository<OrganizationDebt, Long> {
    Optional<OrganizationDebt> findByTin(Long tin);
}
