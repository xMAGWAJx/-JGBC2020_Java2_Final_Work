package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductDatabaseInterface {

    void addProduct(Product product);

    List<Product> getProducts();

    Product findById(int id);

    void deleteById(int id);

    List<Product> getProductByCategory(ProductCategory category);

    Optional<Product> findProductByName(String productName);
}
