package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.atm.entity.tax.Tax;
import uz.atm.enums.TaxStatus;

import java.util.Optional;

/**
 * Author : Khonimov Ulugbek
 * Date : 15.11.2022
 * Time : 12:44 PM
 */

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

    @Transactional
    @Modifying
    @Query( "update tax t set t.status = ?1 where t.id = ?2" )
    void updateStatus(@Nullable TaxStatus status, @Nullable Long id);

    Optional<Tax> findByStir(Long stir);

    @Transactional
    @Modifying
    @Query( "update tax t set t.isSent = ?1 where t.id = ?2" )
    void updateSent(@Nullable Boolean isSent, @Nullable Long id);

}
