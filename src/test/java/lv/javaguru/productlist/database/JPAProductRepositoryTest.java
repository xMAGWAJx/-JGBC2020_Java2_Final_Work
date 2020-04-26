package lv.javaguru.productlist.database;

import junit.framework.TestCase;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JPAProductRepositoryTest extends TestCase {

    @Autowired
    private JPAProductRepository productRepository;

    @Autowired
    private JPAProductListRepository productListRepository;

    @Test
    @Transactional
    public void addProduct() {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);

        Product product = new Product("TestProductAddProductTest", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        assertNull(product.getId());
        product.setProductList(productList);
        productRepository.save(product);
        assertNotNull(product.getId());
        assertTrue(productRepository.findById(product.getId()).isPresent());
    }

    @Test
    @Transactional
    public void findById() {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);

        Product product = new Product("TestProductAddProductTest", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        product.setProductList(productList);
        productRepository.save(product);
        Long productId = product.getId();
        assertTrue(productRepository.findById(productId).isPresent());
    }

    @Test
    @Transactional
    public void deleteById () {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);

        Product product = new Product("TestProductDeleteById", "DescriptionForTestDeleteById",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        product.setProductList(productList);
        productRepository.save(product);
        Long productId = product.getId();
        assertTrue(productRepository.findById(productId).isPresent());
        productRepository.delete(product);
        assertFalse(productRepository.findById(productId).isPresent());
    }

    @Test
    @Transactional
    public void getAllProductsFromDb() {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);
        Product product1 = new Product("TestProductGetAllProducts1", "DescriptionForTestGetAllProducts1",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        product1.setProductList(productList);
        productRepository.save(product1);
        Product product2 = new Product("TestProductGetAllProducts2", "DescriptionForTestGetAllProducts2",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        product2.setProductList(productList);
        productRepository.save(product2);

        productRepository.findByProductList(productList);
        assertEquals(2, productRepository.count());
    }

    @Test
    @Transactional
    public void getProductByCategory () {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);
        Product product1 = new Product("TestProductGetProductByCategory1", "DescriptionForTestGetProductByCategory1",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.VEGETABLES);
        product1.setProductList(productList);
        productRepository.save(product1);
        Product product2 = new Product("TestProductGetProductByCategory2", "DescriptionForTestGetProductByCategory2",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        product2.setProductList(productList);
        productRepository.save(product2);
        Product product3 = new Product("TestProductGetProductByCategory3", "DescriptionForTestGetProductByCategory3",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.VEGETABLES);
        product3.setProductList(productList);
        productRepository.save(product3);
        List<Product> products = productRepository.findByCategory(Category.VEGETABLES);
        assertEquals(2, products.size());
    }


}