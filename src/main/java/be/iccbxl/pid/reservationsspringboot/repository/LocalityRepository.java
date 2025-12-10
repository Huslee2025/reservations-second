package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Locality;

public interface LocalityRepository extends CrudRepository<Locality, Long> {

	List<Locality> findByPostalCode(String postalCode);

	// Insensible à la casse
	List<Locality> findByLocalityContainingIgnoreCase(String locality);

}
