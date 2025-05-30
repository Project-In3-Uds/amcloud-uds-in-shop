package cm.amcloud.mobile.uds_in_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.amcloud.mobile.uds_in_shop.model.Cart;
import cm.amcloud.mobile.uds_in_shop.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
