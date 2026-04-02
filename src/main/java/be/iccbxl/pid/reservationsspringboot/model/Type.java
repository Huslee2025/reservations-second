package be.iccbxl.pid.reservationsspringboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "The type must not be empty.")
    @Size(max = 30, message = "The type must be at most 30 characters long.")
    private String type;

    // Côté propriétaire: c’est ici qu’on décrit la table de liaison
    @ManyToMany
    @JoinTable(name = "artist_type", joinColumns = @JoinColumn(name = "type_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists = new ArrayList<>();

    public Type(String type) {
	this.type = type;
    }

    public List<Artist> getArtists() {
	return artists;
    }

    public Type addArtist(Artist artist) {
	if (artist == null) {
	    return this;
	}
	if (!this.artists.contains(artist)) {
	    this.artists.add(artist);
	}
	if (!artist.getTypes().contains(this)) {
	    artist.getTypes().add(this);
	}
	return this;
    }

    public Type removeArtist(Artist artist) {
	if (artist == null) {
	    return this;
	}
	if (this.artists.remove(artist)) {
	    artist.getTypes().remove(this);
	}
	return this;
    }

    @Override
    public String toString() {
	return type;
    }
}