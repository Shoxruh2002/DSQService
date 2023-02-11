package uz.atm.entity.queueLog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm.entity.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/15/22 3:47 PM
 **/
@Table( schema = "dsq" )
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JuridicMethodResponse extends Auditable {

    @JsonProperty( "ETP_ID" )
    private Integer etpId;

    @JsonProperty( "REQUEST_ID" )
    private Long requestId;

    @JsonProperty( "RESPONSE_ID" )
    private Long responseId;

    @JsonProperty( "METHOD_NAME" )
    private String methodName;

    @Column( columnDefinition = "TEXT" )
    @JsonProperty( "PAYLOAD" )
    private String payload;

}
