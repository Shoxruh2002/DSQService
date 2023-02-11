package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.atm.entity.juridicInfo.JuridicInfoUpdate;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:45 PM
 **/
public interface JuridicInfoUpdateRepository extends JpaRepository<JuridicInfoUpdate, Long> {

    @Transactional( propagation = Propagation.REQUIRES_NEW )
    @Modifying
    @Query( "update JuridicInfoUpdate j set j.updated = ?2 where j.id=?1" )
    void updateById(Long id, boolean updated);
}
