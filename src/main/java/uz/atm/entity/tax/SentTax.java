package uz.atm.entity.tax;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import uz.atm.entity.Auditable;
import uz.atm.enums.TaxStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Author : Khonimov Ulugbek
 * Date : 21.11.2022
 * Time : 11:39 AM
 */
@Entity( name = "sent_tax" )
@Table( schema = "dsq", name = "sent_tax" )
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SentTax extends Auditable {
    @Column( name = "address" )
    private String address;
    @Column( name = "stir" )
    private Long stir;
    @Column( name = "name", columnDefinition = "TEXT" )
    private String name;
    @Column( name = "registration_date" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    private Date registrationDate;
    @Column( name = "founder", columnDefinition = "TEXT" )
    private String founder;
    @Column( name = "status" )
    @Enumerated( value = EnumType.STRING )
    private TaxStatus status;
    @Column( name = "is_sent" )
    private Boolean isSent;
}




