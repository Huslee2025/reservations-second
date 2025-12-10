package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
	List<Role> roles = new ArrayList<>();
	roleRepository.findAll().forEach(roles::add);
	return roles;
    }

    public Role getRole(long id) {
	return roleRepository.findById(id).orElse(null);
    }

    public void addRole(Role role) {
	roleRepository.save(role);
    }

    // Role doit déjà porter le bon id
    public void updateRole(Role role) {
	roleRepository.save(role);
    }

    public void deleteRole(long id) {
	roleRepository.deleteById(id);
    }
}
