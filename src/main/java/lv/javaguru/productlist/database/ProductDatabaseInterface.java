package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;

import java.util.List;

public interface ProductDatabaseInterface {

    void addProduct(Product product);

    List<Product> getProducts();

    Product findById(int id);

    void deleteById(int id);
}
