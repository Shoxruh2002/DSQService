package uz.atm.dto.etp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.atm.entity.juridicInfo.JuridicInfo;
import uz.atm.entity.juridicInfo.faunders.Founders;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/14/22 4:28 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JuridicInfoEtpResponse extends BaseResponse {

    public JuridicInfoEtpResponse(Integer etpId, Long requestId, Long responseId, String methodName) {
        super(etpId, requestId, responseId, methodName);
    }

    @JsonProperty( "PAYLOAD" )
    private Payload payload;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {

        @JsonProperty( "STATUS" )
        private String status;

        @JsonProperty( "ERR_MSG" )
        private String errMsg;

        @JsonProperty( "juridicInfoResponseDto" )
        private JuridicInfo juridicInfoResponseDto;

        @JsonProperty( "founder_list" )
        private List<Founders> founders;

    }

}
