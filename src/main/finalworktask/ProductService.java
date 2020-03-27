package main.finalworktask;

import java.util.List;

public class ProductService implements ProductServiceInterface {

    private ProductDatabase database;

    public ProductService(ProductDatabase database) {
        this.database = database;
    }

    public AddProductResponse addProduct(Product product) {

        if (product.getName() == null
            || product.getName().equals("")) {
            return new AddProductResponse(false, "Empty product name!");
        }

        // add product to Database!!!
        database.addProduct(product);

        return new AddProductResponse(true, null);
    }


    public List<Product> getProductList() {
        return database.getProducts();
    }

    public Product findById(int id) {
        return database.findById(id);
    }

}
