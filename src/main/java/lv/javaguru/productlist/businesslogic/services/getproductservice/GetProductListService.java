package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductListService {

    private ProductDatabase database;

    @Autowired
    public GetProductListService(ProductDatabase database) {
        this.database = database;
    }

    public List<Product> getProductList() {
        return database.getProducts();
    }

}
