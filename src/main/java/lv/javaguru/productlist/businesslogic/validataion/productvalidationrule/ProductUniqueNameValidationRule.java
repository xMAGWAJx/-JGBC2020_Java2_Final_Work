package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductUniqueNameValidationRule implements ProductValidationRuleInterface {

    private JPAProductRepository productRepository;

    @Autowired
    public ProductUniqueNameValidationRule(JPAProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(Product product) {
        Optional<Product> opt = productRepository.findByName(product.getName());
        return !opt.isPresent();
    }

    @Override
    public String errorMessage() {
        return "Product name already present in database! Please add unique product name.";
    }
}
