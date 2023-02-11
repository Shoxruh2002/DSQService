package uz.atm.service;

import org.springframework.stereotype.Service;
import uz.atm.entity.organizationDebt.OrganizationDebtInfo;
import uz.atm.repository.OrganizationDebtInfoRepository;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 12:38 PM
 **/
@Service
public class OrganizationDebtInfoService extends AbstractService<OrganizationDebtInfoRepository> {

    public OrganizationDebtInfoService(OrganizationDebtInfoRepository repository) {
        super(repository);
    }

    public Optional<OrganizationDebtInfo> getByTin(Long tin) {
        return repository.findByTin(tin);
    }
}
