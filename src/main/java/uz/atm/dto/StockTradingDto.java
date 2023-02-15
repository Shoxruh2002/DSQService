package uz.atm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 28/09/22
 * Time: 10:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class StockTradingDto {

    @JsonProperty( "lotInfo" )
    private LotInfo lotInfo;

    @JsonProperty( "seller" )
    private Participant seller;

    @JsonProperty( "buyer" )
    private Participant buyer;

    @JsonProperty( "products" )
    private List<Product> products;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LotInfo {

        @JsonProperty( "id" )
        private Long id;

        @JsonProperty( "name" )
        private String name;

        @JsonProperty( "status_name" )
        private String statusName;

        @JsonProperty( "dateini" )
        private Long dateini;

        @JsonProperty( "date_end" )
        private Long dateEnd;

        @JsonProperty( "item_group_name" )
        private String itemGroupName;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Participant {

        @JsonProperty( "name" )
        private String name;

        @JsonProperty( "inn" )
        private String inn;

        @JsonProperty( "account" )
        private String account;

        @JsonProperty( "region" )
        private String region;

        @JsonProperty( "rayon" )
        private String rayon;

        @JsonProperty( "address" )
        private String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        @JsonProperty( "art_id" )
        private String artId;

        @JsonProperty( "p_product_name" )
        private String pProductName;

        @JsonProperty( "measure_gnk" )
        private Long measureGnk;

        @JsonProperty( "quantity" )
        private Long quantity;

        @JsonProperty( "one_price" )
        private Long onePrice;

        @JsonProperty( "all_price" )
        private Long allPrice;

        @JsonProperty( "descript" )
        private String descript;

        @JsonProperty( "tnvd_code" )
        private String tnvdCode;

        @JsonProperty( "product_code" )
        private String productCode;

        @JsonProperty( "expend_id" )
        private String expendId;

        @JsonProperty( "name" )
        private String name;

        @JsonProperty( "plan_position_id" )
        private String planPositionId;

        @JsonProperty( "product_properties" )
        private List<Properties> productProperties;
    }
}
