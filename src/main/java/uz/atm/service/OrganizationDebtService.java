package uz.atm.service;

import org.springframework.stereotype.Service;
import uz.atm.entity.organizationDebt.OrganizationDebt;
import uz.atm.repository.OrganizationDebtRepository;

import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 12:38 PM
 **/
@Service
public class OrganizationDebtService extends AbstractService<OrganizationDebtRepository> {

    public OrganizationDebtService(OrganizationDebtRepository repository) {
        super(repository);
    }

    public Optional<OrganizationDebt> getByTin(Long tin) {
        return repository.findByTin(tin);
    }
}
