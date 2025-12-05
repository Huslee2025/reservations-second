package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Type;
import be.iccbxl.pid.reservationsspringboot.repository.TypeRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository typeRepository;

	public List<Type> getAllTypes() {
		List<Type> types = new ArrayList<>();
		typeRepository.findAll().forEach(types::add);
		return types;
	}

	public Type getType(long id) {
		return typeRepository.findById(id).orElse(null);
	}

	public void addType(Type type) {
		typeRepository.save(type);
	}

	// l'objet Type doit déjà contenir le bon id
	public void updateType(Type type) {
		typeRepository.save(type);
	}

	public void deleteType(long id) {
		typeRepository.deleteById(id);
	}
}
