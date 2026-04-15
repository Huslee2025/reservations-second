package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.findByLoginWithRoles(username);

	if (user == null) {
	    throw new UsernameNotFoundException("User " + username + " not found");
	}

	return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
		buildAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> buildAuthorities(List<Role> roles) {
	List<GrantedAuthority> authorities = new ArrayList<>();
	if (roles == null)
	    return authorities;

	for (Role r : roles) {
	    if (r == null || r.getRole() == null)
		continue;

	    String roleName = r.getRole().trim().toUpperCase();
	    if (roleName.startsWith("ROLE_")) {
		roleName = roleName.substring(5);
	    }
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
	}
	return authorities;
    }
}