package uz.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.atm.entity.tax.SentTax;


@Repository
public interface SentTaxRepository extends JpaRepository<SentTax, Long> {

}