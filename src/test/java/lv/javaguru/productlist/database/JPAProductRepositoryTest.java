package lv.javaguru.productlist.database;

import junit.framework.TestCase;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JPAProductRepositoryTest extends TestCase {

    @Autowired
    private JPAProductRepository productRepository;

    @Test
    public void addProduct() {
        Product product = new Product("TestProductAddProductTest", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        assertNull(product.getId());
        productRepository.save(product);
        assertNotNull(product.getId());
        assertTrue(productRepository.findById(product.getId()).isPresent());
    }

    @Test
    public void findById() {
        Product product = new Product("TestProductAddProductTest", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        productRepository.save(product);
        Long productId = product.getId();
        assertTrue(productRepository.findById(productId).isPresent());
    }

}