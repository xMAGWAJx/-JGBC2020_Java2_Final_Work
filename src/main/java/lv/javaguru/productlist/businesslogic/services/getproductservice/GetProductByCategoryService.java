package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductByCategoryService {

    private ProductDatabase database;

    @Autowired
    public GetProductByCategoryService(ProductDatabase database) {
        this.database = database;
    }

    public List<Product> getProductByCategory(ProductCategory category) {
        return database.getProductByCategory(category);
    }
}
