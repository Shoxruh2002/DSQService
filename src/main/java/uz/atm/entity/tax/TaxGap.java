package uz.atm.entity.tax;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 6:53 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "dsq" )
public class TaxGap extends Auditable {

    @JsonProperty( "yur_name" )
    private String yurName;

    @JsonProperty( "tin" )
    private Long tin;

    @JsonProperty( "reg_date" )
    private Date regDate;

    @JsonProperty( "tax_gap" )
    private String taxGap;

    @JsonProperty( "vat_certificate" )
    private Integer vatCertificate;

    @JsonProperty( "vat_certificate_state" )
    private Integer vatCertificateState;

    @JsonProperty( "tax_debt" )
    private String taxDebt;

    private Integer etpId;

    private Long etpRequestId;

    //    @Column( columnDefinition = "default 0" )
    private Long responseId;

}
