package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.dto.UserProfileDto;
import be.iccbxl.pid.reservationsspringboot.dto.UserRegistrationDto;
import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.RoleRepository;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
	List<User> users = new ArrayList<>();
	userRepository.findAll().forEach(users::add);
	return users;
    }

    public User getUser(long id) {
	return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
	return userRepository.findByEmail(email).orElse(null);
    }

    public boolean isLoginAndEmailAvailable(String login, String email) {
	return !userRepository.existsByLogin(login) && !userRepository.existsByEmail(email);
    }

    public void registerFromDto(UserRegistrationDto dto) {
	User user = new User();
	user.setFirstname(dto.getFirstname());
	user.setLastname(dto.getLastname());
	user.setLogin(dto.getLogin());
	user.setEmail(dto.getEmail());
	user.setLangue(dto.getLangue());
	user.setPassword(passwordEncoder.encode(dto.getPassword()));

	// Avant: user.setRole(UserRole.MEMBER);
	// Maintenant: on ajoute un Role "MEMBER" via la table roles + table de liaison
	Role memberRole = roleRepository.findByRoleIgnoreCase("MEMBER");
	if (memberRole == null) {
	    throw new RuntimeException("Rôle introuvable: MEMBER");
	}
	user.addRole(memberRole);

	userRepository.save(user);
    }

    public void updateUser(long id, User user) {
	User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

	existing.setFirstname(user.getFirstname());
	existing.setLastname(user.getLastname());
	existing.setEmail(user.getEmail());
	existing.setLangue(user.getLangue());

	userRepository.save(existing);
    }

    public void updateUserFromDto(UserProfileDto dto) {
	User user = userRepository.findById(dto.getId())
		.orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

	user.setFirstname(dto.getFirstname());
	user.setLastname(dto.getLastname());
	user.setEmail(dto.getEmail());
	user.setLangue(dto.getLangue());

	if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
	    user.setPassword(passwordEncoder.encode(dto.getPassword()));
	}

	userRepository.save(user);
    }

    public void deleteUser(long id) {
	userRepository.deleteById(id);
    }

    public void deleteByLogin(String login) {
	User user = userRepository.findByLogin(login);
	if (user != null) {
	    userRepository.delete(user);
	}
    }
}