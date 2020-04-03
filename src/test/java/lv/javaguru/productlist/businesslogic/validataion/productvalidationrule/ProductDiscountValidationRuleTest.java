package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountValidationRuleTest {

    private ProductDiscountValidationRule rule = new ProductDiscountValidationRule();

    @Test
    public void returnTrueDiscountIsBiggerThanZero() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnTrueDiscountIsBiggerThanZeroAndLessOrEqualToHundred() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(100), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnTrueDiscountIsZero() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(0), Category.FRUIT);
        assertTrue(rule.isValid(product));
    }

    @Test
    public void returnFalseWhenDiscountIsBiggerThanHundred() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(120), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product discount!", rule.errorMessage());
    }

    @Test
    public void returnFalseWhenDiscountIsNegative() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(-10), Category.FRUIT);
        assertFalse(rule.isValid(product));
        assertEquals("Incorrect product discount!", rule.errorMessage());
    }

}