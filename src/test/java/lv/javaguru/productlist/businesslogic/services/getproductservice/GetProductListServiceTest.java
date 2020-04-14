package lv.javaguru.productlist.businesslogic.services.getproductservice;


import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetProductListServiceTest {

    private ProductDatabase database;
    private GetProductListService service;

    @Before
    public void setup() {
        database = mock(ProductDatabase.class);
        service = new GetProductListService(database);
    }

    /*@Test
    public void returnTrueWhenProductsArePresentInList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("TestName1", "TestDescription1", BigDecimal.valueOf(30), BigDecimal.valueOf(10), Category.FRUIT));
        productList.add(new Product("TestName2", "TestDescription2", BigDecimal.valueOf(40), BigDecimal.valueOf(20), Category.FRUIT));

        when(service.getProductList()).thenReturn(productList);

        List<Product> result = service.getProductList();
        assertEquals(productList, result);
    }*/

    @Test
    public void returnFalseWhenProductListIsEmpty() {
        List<Product> productList = new ArrayList<>();

        when(service.getProductList()).thenReturn(productList);

        List<Product> result = service.getProductList();
        assertEquals(productList, result);
        assertTrue(result.isEmpty());
        assertTrue(productList.isEmpty());
    }

}