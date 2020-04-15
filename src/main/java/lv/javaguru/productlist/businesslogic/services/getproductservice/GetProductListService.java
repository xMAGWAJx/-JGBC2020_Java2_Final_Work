package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductListService {

    private ProductRepository database;

    @Autowired
    public GetProductListService(ProductRepository database) {
        this.database = database;
    }

    public List<Product> getProductList() {
        return database.getProducts();
    }

}
