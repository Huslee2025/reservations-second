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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = { "roles", "representations", "reviews" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 2)
    private String langue;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "email_verified_at")
    private LocalDateTime email_verified_at;

    @Column(name = "remember_token")
    private String remember_token;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Representation> representations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    public User addRole(Role role) {
	if (role != null && !this.roles.contains(role)) {
	    this.roles.add(role);
	    role.addUser(this);
	}
	return this;
    }

    public User removeRole(Role role) {
	if (role != null && this.roles.contains(role)) {
	    this.roles.remove(role);
	    role.getUsers().remove(this);
	}
	return this;
    }

    public User addRepresentation(Representation representation) {
	if (representation != null && !this.representations.contains(representation)) {
	    this.representations.add(representation);
	    representation.addUser(this);
	}
	return this;
    }

    public User removeRepresentation(Representation representation) {
	if (representation != null && this.representations.contains(representation)) {
	    this.representations.remove(representation);
	    representation.getUsers().remove(this);
	}
	return this;
    }

    public User addReview(Review review) {
	if (review != null && !this.reviews.contains(review)) {
	    this.reviews.add(review);
	    review.setUser(this);
	}
	return this;
    }

    public User removeReview(Review review) {
	if (review != null && this.reviews.remove(review)) {
	    review.setUser(null);
	}
	return this;
    }

    @Override
    public String toString() {
	return login + " (" + firstname + " " + lastname + ")";
    }
}