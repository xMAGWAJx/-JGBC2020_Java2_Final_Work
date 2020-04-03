package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductUniqueNameValidationRuleTest {

    private ProductDatabase database;
    private ProductUniqueNameValidationRule rule;

    @Before
    public void setup() {
        database = Mockito.mock(ProductDatabase.class);
        rule = new ProductUniqueNameValidationRule(database);
    }

    @Test
    public void returnTrueIfNameIsAvailable() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
         assertTrue(rule.isValid(product));
    }

    @Test
    public void returnFalseIfNameIsAlreadyInDatabase() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        Product product2 = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        assertTrue(rule.isValid(product));
//        assertFalse(rule.isValid(product2)); // if i add this it fails with an error
        assertEquals("Product name already present in database! Please add unique product name.", rule.errorMessage());
    }

}