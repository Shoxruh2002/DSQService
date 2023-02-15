package uz.atm.entity.juridicInfo;


import lombok.*;
import uz.atm.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:46
 */

@Entity(name = "addresses")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "dsq", name = "addresses" )
public class Address extends Auditable {

    private String countryCode;

    private String soatoCode;

    private String village;

    private String sectorCode;

    private String streetName;

    private String house;

    private String flat;

    private Integer postcode;

    private String cadastreNumber;

}
