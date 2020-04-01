package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

import java.util.List;

public class GetProductListService {

    private ProductDatabase database;

    public GetProductListService(ProductDatabase database) {
        this.database = database;
    }

    public List<Product> getProductList() {
        return database.getProducts();
    }

}
