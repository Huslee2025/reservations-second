package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "lines", "user" })
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private Long id;

    // reservations.user_id -> users.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "booking_date", nullable = false, updatable = false)
    private LocalDateTime bookingDate;

    @Column(name = "status", nullable = false, length = 60)
    private String status;

    // 1 réservation -> plusieurs lignes representation_reservation
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepresentationReservation> lines = new ArrayList<>();

    @PrePersist
    private void onCreate() {
	if (bookingDate == null) {
	    bookingDate = LocalDateTime.now();
	}
	if (status == null) {
	    status = "PENDING";
	}
    }

    public Reservation addLine(RepresentationReservation line) {
	if (line == null)
	    return this;
	if (!this.lines.contains(line)) {
	    this.lines.add(line);
	}
	line.setReservation(this);
	return this;
    }

    public Reservation removeLine(RepresentationReservation line) {
	if (line == null)
	    return this;

	if (this.lines.remove(line)) {
	    line.setReservation(null);
	}
	return this;
    }
}