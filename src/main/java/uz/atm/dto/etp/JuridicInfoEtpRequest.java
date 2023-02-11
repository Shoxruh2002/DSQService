package uz.atm.dto.etp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/15/22 2:34 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JuridicInfoEtpRequest {

    @JsonProperty("REQUEST_ID")
    private Long requestId;

    @JsonProperty("ETP_ID")
    private Integer etpId;

    @JsonProperty("METHOD_NAME")
    private String methodName;

    @JsonProperty("PAYLOAD")
    private Payload payload;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {

        @JsonProperty("TIN")
        private List<Long> tins;

    }
}
