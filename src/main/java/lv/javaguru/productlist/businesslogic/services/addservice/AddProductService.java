package lv.javaguru.productlist.businesslogic.services.addservice;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationResponse;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddProductService {

    private ProductValidator productValidator;
    private JPAProductRepository productRepository;

    @Autowired
    public AddProductService(JPAProductRepository productRepository,
                             ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Transactional
    public AddProductResponse addProduct(Product product) {
        ProductValidationResponse validationResponse = productValidator.validate(product);
        if (!validationResponse.isSuccess()) {
            return new AddProductResponse(false, validationResponse.getErrorMessages());
        }
        productRepository.save(product);
        return new AddProductResponse(true, null);
    }
}
