package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;

public class GetProductByCategoryService {

    private ProductDatabase database;

    public GetProductByCategoryService(ProductDatabase database) {
        this.database = database;
    }

    public List<Product> getProductByCategory(ProductCategory category) {
        return database.getProductByCategory(category);
    }
}
