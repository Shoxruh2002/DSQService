package uz.atm.entity.tax;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uz.atm.entity.Auditable;
import uz.atm.enums.TaxStatus;

import javax.persistence.*;
import java.util.Date;

@Entity( name = "tax" )
@Table( schema = "dsq", name = "tax" )
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tax extends Auditable {

    @Column( name = "address" )
    private String address;
    @Column( name = "stir", unique = true )
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
    @JsonIgnore
    @Column( name = "is_sent" )
    private Boolean isSent;

}