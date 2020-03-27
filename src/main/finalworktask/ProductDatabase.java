package main.finalworktask;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase implements ProductDatabaseInterface{
    private int currentID = 0;
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        product.setId(currentID);
        products.add(product);
        this.currentID++;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findById (int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
    }

}
