package uz.atm.entity.individualInfoResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.atm.entity.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 10/25/22 11:28 AM
 **/
@Entity
@Table( schema = "dsq" )
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualInfo extends Auditable {
    // TODO: 2/9/23 Bowqattan yoziw kk api response uzgaribdi ekan
    private Long pinfl;//

    private Long tin;//

    private String name;//

    private Integer isEntrepreneur;//

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date registrationDate;//

    private Long registrationId;//

    @Column( columnDefinition = "TEXT" )
    private String activityTypeName;//

    private Integer status;//

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date liquidationDate;//

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date suspensionDate;//

    private String activityTypeId;//

    private String vatNumber;//

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date beginDate;//

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date endDate;//

    @Embedded
    private IndividualAddress entrepreneurshipAddress;


    @Embeddable
    @Data
    static class IndividualAddress {

        private String soatoCode;

        private Integer regionId;

        private Integer districtId;

        @Column( columnDefinition = "TEXT" )
        private String address;
    }
}
