package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
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

    private InMemoryProductRepositoryImpl database;
    private GetProductByIdService service;

    @Before
    public void setup() {
        database = mock(InMemoryProductRepositoryImpl.class);
        service = new GetProductByIdService(database);
    }

    @Test
    public void returnTrueWhenFindByIdIsSuccessful() {
        Product product1 = new Product("TestName1", "TestDescription1", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        Product product2 = new Product("TestName2", "TestDescription2", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT);
        when(service.findById(1L)).thenReturn(Optional.of(product2));
        Optional<Product> optionalProduct = service.findById(1L);
        assertTrue(optionalProduct.isPresent());
        assertEquals(product2, optionalProduct.get());
    }

    @Test
    public void returnFalseWhenFindByIdCantFindEntry() {
        when(service.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> product = service.findById(1L);
        assertFalse(product.isPresent());
    }
}