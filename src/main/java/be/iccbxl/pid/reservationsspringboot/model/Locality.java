package be.iccbxl.pid.reservationsspringboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "localities")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // pas de setId
    private Long id;

    private String postalCode;

    private String locality;

    @OneToMany(mappedBy = "locality")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Location> locations = new ArrayList<>();

    public Locality(String postalCode, String locality) {
	this.postalCode = postalCode;
	this.locality = locality;
    }

    public void addLocation(Location location) {
	if (location == null)
	    return;
	if (!locations.contains(location)) {
	    locations.add(location);
	}
    }

    public void removeLocation(Location location) {
	locations.remove(location);
    }
}
