package uz.atm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/10/23 9:44 AM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( schema = "dsq" )
public class PurchaseInfo extends Auditable {

    private String account;

    @JsonProperty( "bank_code" )
    private Long bankCode;

    @JsonProperty( "buyer_code" )
    private String buyerCode;

    @JsonProperty( "contract_date" )
    private String contractDate;

    @JsonProperty( "contract_id" )
    private String contractId;

    @JsonProperty( "contract_number" )
    private String contractNumber;

    @JsonProperty( "contract_sum" )
    private Long contractSum;

    @JsonProperty( "contract_text" )
    private String contractText;

    @JsonProperty( "contract_type" )
    private String contractType;

    @JsonProperty( "customer_name" )
    private String customerName;

    @JsonProperty( "customer_tin" )
    private Long customerTin;

    @JsonProperty( "exucutor_name" )
    private String exucutorName;

    @JsonProperty( "exucutor_tin" )
    private Long exucutorTin;

    @JsonProperty( "facture_date" )
    private String factureDate;

    @JsonProperty( "facture_number" )
    private String factureNumber;

    @JsonProperty( "facture_sum" )
    private Long factureSum;

    @JsonProperty( "lot_number" )
    private String lotNumber;

    @JsonProperty( "vat_sum" )
    private Long vatSum;

    @JsonProperty( "with_vat_sum" )
    private Long withVatSum;

}
