package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductPriceValidationRuleTest {

    private ProductPriceValidationRule rule = new ProductPriceValidationRule();

    @Test
    public void returnTrueWhenPriseIsFilledCorrectly() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnFalseWhenPriseIsZero() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(0), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product price!", rule.errorMessage());
    }

    @Test
    public void returnFalseWhenPriseIsNegative() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(-10), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product price!", rule.errorMessage());
    }

}