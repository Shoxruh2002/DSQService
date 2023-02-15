package uz.atm.entity.juridicInfo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:36
 */
@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Director {
    @Column(name = "director_last_name")
    private String lastName;//

    @Column(name = "director_first_name")
    private String firstName;//

    @Column(name = "director_middle_name")
    private String middleName;//

    @Column(name = "director_gender")
    private Integer gender;//

    @Column(name = "director_country_code")
    private String countryCode;//

    @Column(name = "director_passport_series")
    private String passportSeries;

    @Column(name = "director_passport_number")
    private String passportNumber;

    @Column(name = "director_pinfl")
    private Long pinfl;//


    @Column(name = "director_tin")
    private Long tin;//

}
