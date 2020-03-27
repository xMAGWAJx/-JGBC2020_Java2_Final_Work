package main.finalworktask;

import java.util.List;

public interface ProductServiceInterface {

    AddProductResponse addProduct(Product product);

    List<Product> getProductList();

    Product findById(int id);
}
