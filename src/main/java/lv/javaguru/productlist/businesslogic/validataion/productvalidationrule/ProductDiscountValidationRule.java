package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRuleInterface {

    @Override
    public boolean isValid(Product product) {
        return (product.getDiscount().compareTo(BigDecimal.ZERO) >= 0) && (product.getDiscount().compareTo(BigDecimal.valueOf(100)) <= 0);
    }

    @Override
    public String errorMessage() {
        return "Incorrect product discount!";
    }
}
