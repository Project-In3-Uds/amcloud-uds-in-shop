package cm.amcloud.mobile.uds_in_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.amcloud.mobile.uds_in_shop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
