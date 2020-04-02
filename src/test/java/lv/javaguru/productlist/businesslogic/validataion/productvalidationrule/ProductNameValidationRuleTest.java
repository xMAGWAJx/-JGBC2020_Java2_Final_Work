package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductNameValidationRuleTest {

    private ProductNameValidationRule rule = new ProductNameValidationRule();

    @Test
    public void returnFalseWhenNameIsNull() {
        Product product = new Product(null, null, BigDecimal.valueOf(10), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product name!", rule.errorMessage());
    }

    @Test
    public void returnFalseWhenNameIsEmpty() {
        Product product = new Product("", null, BigDecimal.valueOf(10), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product name!", rule.errorMessage());
    }

    @Test
    public void returnTrueWhenNameIsFiledCorrectly() {
        Product product = new Product("Banana", null, BigDecimal.valueOf(20), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnFalseWhenNameIsToShort() {
        Product product = new Product("12", null, BigDecimal.valueOf(20), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product name!", rule.errorMessage());
    }

    @Test
    public void returnFalseWhenNameIsToLong() {
        Product product = new Product("123456789012345678901234567890123", null, BigDecimal.valueOf(20), BigDecimal.valueOf(10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product name!", rule.errorMessage());
    }

}