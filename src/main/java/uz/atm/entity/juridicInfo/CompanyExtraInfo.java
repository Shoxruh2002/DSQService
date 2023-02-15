package uz.atm.entity.juridicInfo;

import lombok.*;

import javax.persistence.Embeddable;
/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:40
 */
@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyExtraInfo {

    private Double monthlyNumberEmployees;

    private Double avgNumberEmployees;
}
