package cm.amcloud.mobile.uds_in_shop.service;

import java.util.List;

import cm.amcloud.mobile.uds_in_shop.model.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
