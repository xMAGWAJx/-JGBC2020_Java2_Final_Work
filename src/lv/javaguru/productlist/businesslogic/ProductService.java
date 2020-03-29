package lv.javaguru.productlist.businesslogic;

import java.util.List;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationResponse;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

public class ProductService {

    private ProductValidator productValidator;
    private ProductDatabase database;

    public ProductService(ProductDatabase database,
                          ProductValidator productValidator) {
        this.database = database;
        this.productValidator = productValidator;
    }

    public AddProductResponse addProduct(Product product) {
        ProductValidationResponse validationResponse =
                productValidator.validate(product);
        if (!validationResponse.isSuccess()) {
            return new AddProductResponse(false, validationResponse.getErrorMessages());
        }
        database.addProduct(product);
        return new AddProductResponse(true, null);
    }

    public List<Product> getProductList() {
        return database.getProducts();
    }

}
