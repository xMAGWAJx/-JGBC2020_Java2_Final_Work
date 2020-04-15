package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryProductRepositoryImpl implements ProductRepository {

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
    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    @Override
    public boolean deleteById(int id) {
        products.removeIf(product -> product.getId() == id);
        return false;
    }

    @Override
    public List<Product> getProductByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public Optional<Product> findProductByName(String productName) {
        return products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst();
    }
}
