package lv.javaguru.productlist.database;

import lv.javaguru.productlist.SpringConfig;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:cleanAllTables.sql")
})
public class JDBCDatabaseIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddProductToDB() {
        Product product = new Product("Milk", "1L pack",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        assertNull(product.getId());
        productRepository.addProduct(product);
        assertNotNull(product.getId());
    }

    @Test
    public void shouldGetProductsFromDB() {
        List<Product> products = productRepository.getProducts();
        assertFalse(products.isEmpty());
    }

}
