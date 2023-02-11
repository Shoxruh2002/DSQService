package uz.atm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:01 PM
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaxGapDto {

    @JsonProperty( "yur_name" )
    private String yurName;

    @JsonProperty( "tin" )
    private Long tin;

    @JsonProperty( "reg_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date regDate;

    @JsonProperty( "tax_gap" )
    private String taxGap;

    @JsonProperty( "vat_certificate" )
    private Integer vatCertificate;

    @JsonProperty( "vat_certificate_state" )
    private Integer vatCertificateState;

    @JsonProperty( "tax_debt" )
    private String taxDebt;

}
