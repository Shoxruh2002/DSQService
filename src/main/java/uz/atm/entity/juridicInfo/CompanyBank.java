package uz.atm.entity.juridicInfo;

import lombok.*;
import uz.atm.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:50
 */

@Entity( name = "company_banks" )
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "dsq", name = "company_banks" )
public class CompanyBank extends Auditable {

    private String mfo;

    private String paymentAccount;

    private Integer status;

    private String openDate;

    private String closeDate;

    private Integer attribute;

    private Integer reasonCode;

    private String currencyCode;
}
