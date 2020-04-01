package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase implements ProductDatabaseInterface {
    private int currentID = 1;
    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        product.setId(currentID);
        products.add(product);
        this.currentID++;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteById(int id) {
        products.removeIf(product -> product.getId() == id);
    }

}
