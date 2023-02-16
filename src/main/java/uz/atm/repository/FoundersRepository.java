package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.atm.entity.juridicInfo.faunders.Founders;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 12:34 PM
 **/
public interface FoundersRepository extends JpaRepository<Founders, Long> {

    @Transactional
    void deleteAllByCompanyTin(Long companyTin);
}
