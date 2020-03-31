package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.domain.Product;

public class ProductNameValidationRuleInterface implements ProductValidationRuleInterface {

    @Override
    public boolean isValid(Product product) {
        return product.getName() != null
                && !product.getName().equals("")
                && product.getName().length() >= 3
                && product.getName().length() <= 32;
    }

    @Override
    public String errorMessage() {
        return "Incorrect product name!";
    }

}
