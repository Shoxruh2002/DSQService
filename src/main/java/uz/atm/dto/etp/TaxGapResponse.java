package uz.atm.dto.etp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.atm.dto.TaxGapDto;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:50 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaxGapResponse extends BaseResponse {
    public TaxGapResponse(Integer etpId, Long requestId, Long responseId, String methodName) {
        super(etpId, requestId, responseId, methodName);
    }

    @JsonProperty( "PAYLOAD" )
    private Payload payload;

    @Data
    @AllArgsConstructor
    public static class Payload {

        @JsonProperty( "STATUS" )
        private String status;

        @JsonProperty( "ERR_MSG" )
        private String errMsg;

        @JsonProperty( "tax_gap" )
        private TaxGapDto taxGap;

    }

}
