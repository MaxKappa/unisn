package epix.app.unisn.repository;

import epix.app.unisn.model.Role;
import epix.app.unisn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User AS u WHERE u.email= ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User AS u WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);

}