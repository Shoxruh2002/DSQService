package uz.atm.entity.organizationDebt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 12:09 PM
 **/
@Table( schema = "dsq" )
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDebt extends Auditable {


    private Long tin;// Tashkilotning stir raqami


    private Long debt;//Tashkilotning Olmoqchi Bolgan qarzi (UZS da)

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "organization_debt_id")
//    private List<Attachment> files;
}
