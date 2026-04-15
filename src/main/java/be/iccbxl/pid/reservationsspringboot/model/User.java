package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private String langue;

    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at;
    private LocalDateTime email_verified_at;
    private String remember_token;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    public User addRole(Role role) {
	if (!this.roles.contains(role)) {
	    this.roles.add(role);
	    role.addUser(this);
	}
	return this;
    }

    public User removeRole(Role role) {
	if (this.roles.contains(role)) {
	    this.roles.remove(role);
	    role.getUsers().remove(this);
	}
	return this;
    }

    @Override
    public String toString() {
	return login + " (" + firstname + " " + lastname + ")";
    }
}