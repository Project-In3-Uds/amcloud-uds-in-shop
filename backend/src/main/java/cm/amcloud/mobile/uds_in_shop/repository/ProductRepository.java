package cm.amcloud.mobile.uds_in_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.amcloud.mobile.uds_in_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
