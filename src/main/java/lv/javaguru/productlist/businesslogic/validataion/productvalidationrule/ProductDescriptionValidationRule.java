package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.domain.Product;

public class ProductDescriptionValidationRule implements ProductValidationRuleInterface {

    @Override
    public boolean isValid(Product product) {
        return product.getDescription() != null
                && !product.getDescription().equals("");
    }

    @Override
    public String errorMessage() {
        return "Empty description!";
    }
}
