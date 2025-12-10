package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.repository.LocalityRepository;

@Service
public class LocalityService {

	@Autowired
	private LocalityRepository localityRepository;

	public List<Locality> getAllLocalities() {
		List<Locality> localities = new ArrayList<>();
		localityRepository.findAll().forEach(localities::add);
		return localities;
	}

	public Locality getLocality(long id) {
		return localityRepository.findById(id).orElse(null);
	}

	public void addLocality(Locality locality) {
		localityRepository.save(locality);
	}

	// l'objet Locality doit déjà avoir le bon id
	public void updateLocality(Locality locality) {
		localityRepository.save(locality);
	}

	public void deleteLocality(long id) {
		localityRepository.deleteById(id);
	}
}
