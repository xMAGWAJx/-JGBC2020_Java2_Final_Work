package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetProductByIdServiceTest {

    private ProductValidator validator;
    private ProductDatabase database;
    private ProductService service;

    @Before
    public void setup() {
        validator = mock(ProductValidator.class);
        database = mock(ProductDatabase.class);
        service = new ProductService(database, validator);
    }

    @Test
    public void returnTrueWhenFindByIdIsSuccessful() {
        Product product1 = new Product("TestName1", "TestDescription1", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        Product product2 = new Product("TestName2", "TestDescription2", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        when(service.findById(1)).thenReturn(Optional.of(product2));
        Optional<Product> optionalProduct = service.findById(1);
        assertTrue(optionalProduct.isPresent());
        assertEquals(product2, optionalProduct.get());
    }

    @Test
    public void returnFalseWhenFindByIdCantFindEntry() {
        when(service.findById(1)).thenReturn(Optional.empty());
        Optional<Product> product = service.findById(1);
        assertFalse(product.isPresent());
    }
}