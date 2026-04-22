package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "representation_reservation")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "reservation", "representation", "price" })
public class RepresentationReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private Long id;

    // representation_reservation.reservation_id -> reservations.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    // representation_reservation.representation_id -> representations.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "representation_id", nullable = false)
    private Representation representation;

    // representation_reservation.price_id -> prices.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}