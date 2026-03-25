package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Representation;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.repository.RepresentationRepository;

@Service
public class RepresentationService {

    @Autowired
    private RepresentationRepository repository;

    public List<Representation> getAll() {
	List<Representation> representations = new ArrayList<>();
	repository.findAll().forEach(representations::add);
	return representations;
    }

    public Representation get(Long id) {
	return repository.findById(id).orElse(null);
    }

    public void add(Representation representation) {
	repository.save(representation);
    }

    public void update(Representation representation) {
	repository.save(representation);
    }

    public void delete(Long id) {
	repository.deleteById(id);
    }

    public List<Representation> getFromLocation(Location location) {
	return repository.findByLocation(location);
    }

    public List<Representation> getFromShow(Show show) {
	return repository.findByShow(show);
    }
}