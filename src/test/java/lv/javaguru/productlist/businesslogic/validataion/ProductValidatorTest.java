package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductValidatorTest {

    private ProductValidator validator;

    @Test
    public void shouldReturnFalseResultOneRule() {
        ProductValidationRuleInterface rule = mock(ProductValidationRuleInterface.class);

        List<ProductValidationRuleInterface> rules = new ArrayList<>();
        rules.add(rule);

        validator = new ProductValidator(rules);

        Product product = new Product("test", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);

        when(rule.isValid(product)).thenReturn(false);
        when(rule.errorMessage()).thenReturn("Error");

        ProductValidationResponse response = validator.validate(product);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessages().size(), 1);
        assertTrue(response.getErrorMessages().contains("Error"));
    }

    @Test
    public void shouldReturnFalseResultTwoRules() {
        ProductValidationRuleInterface rule1 = mock(ProductValidationRuleInterface.class);
        ProductValidationRuleInterface rule2 = mock(ProductValidationRuleInterface.class);

        List<ProductValidationRuleInterface> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);

        validator = new ProductValidator(rules);

        Product product = new Product("test", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);

        when(rule1.isValid(product)).thenReturn(false);
        when(rule1.errorMessage()).thenReturn("Error 1");

        when(rule2.isValid(product)).thenReturn(false);
        when(rule2.errorMessage()).thenReturn("Error 2");

        ProductValidationResponse response = validator.validate(product);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessages().size(), 2);
        assertTrue(response.getErrorMessages().contains("Error 1"));
        assertTrue(response.getErrorMessages().contains("Error 2"));
    }

    @Test
    public void shouldReturnTrueResultOneRule() {
        ProductValidationRuleInterface rule = mock(ProductValidationRuleInterface.class);

        List<ProductValidationRuleInterface> rules = new ArrayList<>();
        rules.add(rule);

        validator = new ProductValidator(rules);

        Product product = new Product("test", "TestDescription", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);

        when(rule.isValid(product)).thenReturn(true);

        ProductValidationResponse response = validator.validate(product);
        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessages());

        verify(rule, times(0)).errorMessage();
    }

}