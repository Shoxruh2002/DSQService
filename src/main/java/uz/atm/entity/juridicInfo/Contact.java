package uz.atm.entity.juridicInfo;

import lombok.*;
import uz.atm.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 11:42
 */

@Entity( name = "contacts" )
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table( schema = "dsq", name = "contacts" )
public class Contact extends Auditable {

    private String phone;

    private String email;
}
