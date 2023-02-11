package uz.atm.dto.etp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:38 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaxGapRequest {

    @JsonProperty( "REQUEST_ID" )
    private Long requestId;

    @JsonProperty( "ETP_ID" )
    private Integer etpId;

    @JsonProperty( "METHOD_NAME" )
    private String methodName;

    @JsonProperty( "PAYLOAD" )
    private Payload payload;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {

        @JsonProperty( "TIN" )
        private Long tin;

    }


}
