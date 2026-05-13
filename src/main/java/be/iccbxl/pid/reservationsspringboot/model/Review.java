package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "show"})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private Long id;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    
    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    
    @Column(name = "stars", nullable = false)
    private Integer stars;

    
    @Column(name = "validated", nullable = false)
    private boolean validated;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (created_at == null) created_at = now;
        if (updated_at == null) updated_at = now;
    }

    @PreUpdate
    private void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}