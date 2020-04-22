package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { SpringConfig.class })
//@TransactionConfiguration(defaultRollback = false)
@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JDBCDatabaseIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void shouldAddProductToDB() {
        Product product = new Product("TestProductAddProductTest", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        assertNull(product.getId());
        productRepository.addProduct(product);
        assertEquals("TestProductAddProductTest", product.getName());
        assertEquals("DescriptionForTestProductAddProductTest", product.getDescription());
        assertNotNull(product.getId());
    }

    @Test
    @Sql(scripts = {"/db/createTestDataForGetProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void shouldGetProductsFromDB() {
        List<Product> products = productRepository.getProducts();
        assertFalse(products.isEmpty());
        assertEquals(3, products.size());
    }

    @Test
    public void shouldGetProductById() {
        Product product = new Product("TestProductFindById", "DescriptionFindById",
                BigDecimal.valueOf(70), BigDecimal.valueOf(60), Category.DRINKS);
        productRepository.addProduct(product);
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        assertTrue(optionalProduct.isPresent());
        assertEquals(product.getId(), optionalProduct.get().getId());
        assertEquals("TestProductFindById", product.getName());
        assertEquals("DescriptionFindById", product.getDescription());
        assertEquals(BigDecimal.valueOf(70), product.getPrice());
        assertEquals(BigDecimal.valueOf(60), product.getDiscount());
        assertEquals(Category.DRINKS, product.getCategory());
        assertEquals(BigDecimal.valueOf(28), product.getActualPrice());
    }

    @Test
    public void shouldDeleteProductById() {
        Product product = new Product("TestProductToDelete", "DescriptionProductToDelete", BigDecimal.valueOf(40), BigDecimal.valueOf(20), Category.FRUIT);
        productRepository.addProduct(product);
        assertEquals("TestProductToDelete", product.getName());
        assertEquals("DescriptionProductToDelete", product.getDescription());
        assertTrue(productRepository.deleteById(product.getId()));
        assertFalse(productRepository.findById(product.getId()).isPresent());
    }
}