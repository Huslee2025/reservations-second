package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.repository.LocationRepository;

@Service
public class LocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> getAll() {
	List<Location> locations = new ArrayList<>();

	repository.findAll().forEach(locations::add);

	return locations;
    }

    public Location get(Long id) {
	return repository.findById(id).orElse(null);
    }

    public Location getBySlug(String slug) {
	return repository.findBySlug(slug).orElse(null);
    }

    public void add(Location location) {
	repository.save(location);
    }

    public void update(Location location) {
	repository.save(location);
    }

    public void delete(Long id) {
	repository.deleteById(id);
    }
}
