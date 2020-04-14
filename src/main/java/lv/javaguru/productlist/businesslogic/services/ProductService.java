package lv.javaguru.productlist.businesslogic.services;

import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductByCategoryService;
import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductListService;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService implements ProductServiceInterface {

    private ProductDatabase database;

    @Autowired
    public ProductService(ProductDatabase database) {
        this.database = database;
    }

    @Override
    public List<Product> getProductList() {
        return new GetProductListService(database).getProductList();
    }

    @Override
    public List<Product> getProductByCategory(ProductCategory category) {
        return new GetProductByCategoryService(database).getProductByCategory(category);
    }
}
