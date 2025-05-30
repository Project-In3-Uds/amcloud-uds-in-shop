package cm.amcloud.mobile.uds_in_shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.amcloud.mobile.uds_in_shop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByDeletedFalse(); // Fetch only non-deleted users
}
