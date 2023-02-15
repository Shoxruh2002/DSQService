package uz.atm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Properties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("PROP_NUMB")
	private Integer propNumb;
	@JsonProperty("PROP_NAME")
	private String propName;
	@JsonProperty("VAL_NUMB")
	private Integer valNumb;
	@JsonProperty("VAL_NAME")
	private String valName;
}