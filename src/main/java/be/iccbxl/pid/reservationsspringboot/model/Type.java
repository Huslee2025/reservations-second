package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;

	@NotBlank(message = "The type must not be empty.")
	@Size(max = 30, message = "The type must be at most 30 characters long.")
	private String type;

	public Type(String type) {
		this.type = type;
	}
}
