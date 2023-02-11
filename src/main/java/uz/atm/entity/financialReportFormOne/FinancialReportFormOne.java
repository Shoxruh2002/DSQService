package uz.atm.entity.financialReportFormOne;

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
 * Time: 14:53
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Table( schema = "dsq", indexes = {
        @Index( name = "accounting_balance_info_response_year_index", columnList = "year" ),
        @Index( name = "accounting_balance_info_response_quarter_index", columnList = "quarter" ),
        @Index( name = "accounting_balance_info_response_tin_index", columnList = "tin" )
} )
public class FinancialReportFormOne extends Auditable {

    private Integer year;

    private Integer quarter;

    @JsonProperty( "ptype" )
    public String pType;    //hisobot turi;

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
    private Long s011;
    private Long s012;
    private Long s020;
    private Long s021;
    private Long s022;
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
    private Long s211;
    private Long s220;
    private Long s230;
    private Long s240;
    private Long s250;
    private Long s260;
    private Long s270;
    private Long s280;
    private Long s290;
    private Long s300;
    private Long s310;
    private Long s320;
    private Long s330;
    private Long s340;
    private Long s350;
    private Long s360;
    private Long s370;
    private Long s380;
    private Long s390;
    private Long s400;
    private Long s410;
    private Long s420;
    private Long s430;
    private Long s440;
    private Long s450;
    private Long s460;
    private Long s470;
    private Long s480;
    private Long s490;
    private Long s491;
    private Long s500;
    private Long s510;
    private Long s520;
    private Long s530;
    private Long s540;
    private Long s550;
    private Long s560;
    private Long s570;
    private Long s580;
    private Long s590;
    private Long s600;
    private Long s601;
    private Long s602;
    private Long s610;
    private Long s620;
    private Long s630;
    private Long s640;
    private Long s650;
    private Long s660;
    private Long s670;
    private Long s680;
    private Long s690;
    private Long s700;
    private Long s710;
    private Long s720;
    private Long s730;
    private Long s740;
    private Long s750;
    private Long s760;
    private Long s770;
    private Long s780;

}