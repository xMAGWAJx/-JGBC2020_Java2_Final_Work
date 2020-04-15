package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductByCategoryService {

    private ProductRepository database;

    @Autowired
    public GetProductByCategoryService(ProductRepository database) {
        this.database = database;
    }

    public List<Product> getProductByCategory(ProductCategory category) {
        return database.getProductByCategory(category);
    }
}
