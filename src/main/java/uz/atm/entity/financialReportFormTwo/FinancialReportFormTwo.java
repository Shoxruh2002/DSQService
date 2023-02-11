package uz.atm.entity.financialReportFormTwo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.entity.Auditable;
import uz.atm.enums.FinancialReportStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 15:10
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Table( schema = "dsq", indexes = {
        @Index( name = "enterprise_and_organization_info_response_quarter_index", columnList = "quarter" ),
        @Index( name = "enterprise_and_organization_info_response_year_index", columnList = "year" ),
        @Index( name = "enterprise_and_organization_info_response_tin_index", columnList = "tin" )

} )
public class FinancialReportFormTwo extends Auditable {

    private Integer year;

    private Integer quarter;

    private String pType;

    private Long tin;

    @JsonProperty( "ns10code" )
    private String ns10Code;

    @JsonProperty( "ns11code" )
    private String ns11Code;

    @JsonProperty("datesent")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date dateSent;

    private Long titul;

    private Long s010;
    private Long s020;
    private Long s030;
    private Long s040;
    private Long s050;
    private Long s060;
    private Long s070;
    private Long s080;
    private Long s090;
    private Long s100;
    private Long s110;
    private Long s120;
    private Long s130;
    private Long s140;
    private Long s150;
    private Long s160;
    private Long s170;
    private Long s180;
    private Long s190;
    private Long s200;
    private Long s210;
    private Long s220;
    private Long s230;
    private Long s240;
    private Long s250;
    private Long s260;
    private Long s270;

}