package uz.atm.entity.queueLog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.atm.entity.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/16/22 6:05 PM
 **/
@Table( schema = "dsq" )
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JuridicMethodRequest extends Auditable {

    @JsonProperty( "REQUEST_ID" )
    private Long requestId;

    @JsonProperty( "ETP_ID" )
    private Integer etpId;

    @JsonProperty( "METHOD_NAME" )
    private String methodName;

    @JsonProperty( "PAYLOAD" )
    @Column( columnDefinition = "TEXT" )
    private String payload;

}
