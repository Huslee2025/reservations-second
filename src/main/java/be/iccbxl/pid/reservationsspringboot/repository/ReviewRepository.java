package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.User;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByShow(Show show);

    List<Review> findByUser(User user);
}