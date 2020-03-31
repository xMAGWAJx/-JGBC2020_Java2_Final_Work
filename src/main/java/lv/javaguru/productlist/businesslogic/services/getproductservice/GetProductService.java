package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

import java.util.List;

public class GetProductService {

    private ProductDatabase database;

    public GetProductService(ProductDatabase database) {
        this.database = database;
    }

    public List<Product> getProductList() {
        return database.getProducts();
    }

}
