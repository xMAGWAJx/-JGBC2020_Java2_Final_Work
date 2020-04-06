package lv.javaguru.productlist.businesslogic.services.addservice;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationResponse;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductService {

    private ProductValidator productValidator;
    private ProductDatabase database;

    @Autowired
    public AddProductService(ProductDatabase database, ProductValidator productValidator) {
        this.database = database;
        this.productValidator = productValidator;
    }

    public AddProductResponse addProduct(Product product) {
        ProductValidationResponse validationResponse = productValidator.validate(product);
        if (!validationResponse.isSuccess()) {
            return new AddProductResponse(false, validationResponse.getErrorMessages());
        }
        database.addProduct(product);
        return new AddProductResponse(true, null);
    }
}
