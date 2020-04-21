package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void addProduct(Product product);

    List<Product> getProducts();

    Optional<Product> findById(Long id);

    boolean deleteById(Long id);

    List<Product> getProductByCategory(ProductCategory category);

    Optional<Product> findProductByName(String productName);
}
