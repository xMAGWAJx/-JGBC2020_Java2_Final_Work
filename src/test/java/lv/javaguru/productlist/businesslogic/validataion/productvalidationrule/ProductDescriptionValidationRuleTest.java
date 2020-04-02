package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDescriptionValidationRuleTest {

    private ProductDescriptionValidationRule rule = new ProductDescriptionValidationRule();

    @Test
    public void returnFalseWhenDescriptionIsNull() {
        Product product = new Product("TestName", null, BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Empty description!", rule.errorMessage());
    }

    @Test
    public void returnFalseWhenDescriptionIsEmpty() {
        Product product = new Product("TestName", "", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Empty description!", rule.errorMessage());
    }

    @Test
    public void returnTrueWhenDescriptionIsFilledCorrectly() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

}