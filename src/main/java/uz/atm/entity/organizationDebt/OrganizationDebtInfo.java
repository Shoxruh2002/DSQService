package uz.atm.entity.organizationDebt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.entity.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 11:59 AM
 **/
@Getter
@Table( schema = "dsq" )
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDebtInfo extends Auditable {

    private Long tin;// Tashkilotning stir raqami

    @Column( nullable = false )
    private Long mainPaymentOfDebt;//қарзнинг асосий тўлови

    @Column( nullable = false )
    private Long totalExternalDebt;//жами ташқи қарз

    @Column( nullable = false )
    private Long externalDebtInForeignCurrency;//хорижий валютадаги ташқи қарз

    @Column( nullable = false )
    private Long totalDebt;//жами қарз
}
