package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductUniqueNameValidationRuleTest {

    private InMemoryProductRepositoryImpl database;
    private ProductUniqueNameValidationRule rule;

    @Before
    public void setup() {
        database = mock(InMemoryProductRepositoryImpl.class);
        rule = new ProductUniqueNameValidationRule(database);
    }

    @Test
    public void returnTrueIfNameIsAvailable() {
        Product product = new Product("TestName", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);

        when(database.findProductByName("TestName")).thenReturn(Optional.empty());
         assertTrue(rule.isValid(product));
    }

    @Test
    public void returnFalseIfNameIsNotAvailable() {
        Product product1 = new Product("TestName", "TestDescription1", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        Product product2 = new Product("TestName", "TestDescription2", BigDecimal.valueOf(40), BigDecimal.valueOf(12), Category.VEGETABLES);

        when(database.findProductByName("TestName")).thenReturn(Optional.of(product1));
        assertFalse(rule.isValid(product2));
    }

}