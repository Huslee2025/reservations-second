package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import be.iccbxl.pid.reservationsspringboot.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    List<User> findByLastname(String lastname);

    Optional<User> findByEmail(String email);

    @Query("select u from User u left join fetch u.roles where u.login = :login")
    User findByLoginWithRoles(@Param("login") String login);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
