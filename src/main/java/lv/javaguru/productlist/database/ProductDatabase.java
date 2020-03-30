package lv.javaguru.productlist.database;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.productlist.domain.Product;

public class ProductDatabase {
    private int currentID = 1;
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        product.setId(currentID);
        products.add(product);
        this.currentID++;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }

    public boolean deleteById(int id) {
        return products.remove(products.get(id));
    }

}
