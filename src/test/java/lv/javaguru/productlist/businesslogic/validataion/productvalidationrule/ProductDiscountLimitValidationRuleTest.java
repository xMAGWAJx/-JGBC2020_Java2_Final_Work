package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountLimitValidationRuleTest {

    private ProductDiscountLimitValidationRule rule = new ProductDiscountLimitValidationRule();

    private static final BigDecimal minPriceForDiscount = BigDecimal.valueOf(20);

    @Test
    public void returnTrueInIsValidWhenDiscountIsEligible() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnTrueInIsDiscountAvailableWhenDiscountIsEligible() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isDiscountAvailable(product.getPrice()));
    }

    @Test
    public void returnFalseInIsValidWhenDiscountIsEligible() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(10), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Discount is available for products with price bigger than " +  minPriceForDiscount + "$.", rule.errorMessage());
    }

    @Test
    public void returnFalseInIsDiscountAvailableWhenDiscountIsEligible() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(10), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isDiscountAvailable(product.getPrice()));
    }

}