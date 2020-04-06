package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountLimitValidationRule implements ProductValidationRuleInterface {

    private static final BigDecimal minPriceForDiscount = BigDecimal.valueOf(20);

    public boolean isDiscountAvailable(BigDecimal price) {
        return price.compareTo(minPriceForDiscount) >= 0;
    }

    @Override
    public boolean isValid(Product product) {
        if (product.getDiscount().compareTo(BigDecimal.ZERO) >= 0) {
            return isDiscountAvailable(product.getPrice());
        }
        return true;
    }

    @Override
    public String errorMessage() {
        return "Discount is available for products with price bigger than " + minPriceForDiscount + "$.";
    }
}
