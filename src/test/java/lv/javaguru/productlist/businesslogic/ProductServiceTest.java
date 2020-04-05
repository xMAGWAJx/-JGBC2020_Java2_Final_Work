package lv.javaguru.productlist.businesslogic;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.businesslogic.services.addservice.AddProductResponse;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidationResponse;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

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
    public void shouldReturnFailWhenAddProductValidationFails() {
        List<String> errors = new ArrayList<>();
        errors.add("Name is empty");
        ProductValidationResponse validationResponse = new ProductValidationResponse(false, errors);

        Product product = new Product(null, null, BigDecimal.valueOf(10),BigDecimal.valueOf(20), Category.FRUIT);

        when(validator.validate(product)).thenReturn(validationResponse);

        AddProductResponse response = service.addProduct(product);
        assertFalse(response.isSuccess());
        assertEquals(response.getErrorMessages(), errors);

        verifyZeroInteractions(database);
    }

    @Test
    public void shouldReturnTrueWhenAddProductValidationSuccess() {
         ProductValidationResponse validationResponse = new ProductValidationResponse(true, null);

        Product product = new Product("Banana", "Tasty", BigDecimal.valueOf(10),BigDecimal.valueOf(20), Category.FRUIT);

        when(validator.validate(product)).thenReturn(validationResponse);

        AddProductResponse response = service.addProduct(product);

        assertTrue(response.isSuccess());
        assertNull(response.getErrorMessages());

        verify(database).addProduct(product);
    }


}