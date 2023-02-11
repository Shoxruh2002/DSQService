package uz.atm.dto.etp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/16/23 7:51 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseResponse {

    @JsonIgnore
    private String createdAt = new SimpleDateFormat("dd-MM-yyy mm:HH:ss").format(new Date());

    @JsonProperty( "ETP_ID" )
    private Integer etpId;

    @JsonProperty( value = "REQUEST_ID" )
    private Long requestId;

    @JsonProperty( "RESPONSE_ID" )
    private Long responseId;

    @JsonProperty( "METHOD_NAME" )
    private String methodName;

    public BaseResponse(Integer etpId, Long requestId, Long responseId, String methodName) {
        this.etpId = etpId;
        this.requestId = requestId;
        this.responseId = responseId;
        this.methodName = methodName;
    }
}

