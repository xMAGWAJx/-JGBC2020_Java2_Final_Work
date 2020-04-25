package lv.javaguru.productlist.database;

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

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAProductListRepositoryTest {

    @Autowired
    private JPAProductListRepository productListRepository;

    @Autowired
    private JPAProductRepository productRepository;

    @Test
    @Transactional
    @Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void createProductList() {
        ProductList productList = new ProductList("Partika", "Monday shopping");
        productListRepository.save(productList);

        Product milk = new Product("Milk", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        milk.setProductList(productList);
        productRepository.save(milk);

        Product bear = new Product("Bear", "DescriptionForTestProductAddProductTest",
                BigDecimal.valueOf(1.0), BigDecimal.valueOf(0L), Category.DRINKS);
        bear.setProductList(productList);
        productRepository.save(bear);
    }

    @Test
    @Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/db/createTestDataForGetProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void searchProduct() {
        Optional<Product> p = productRepository.findByName("Milk");
        assertNotNull(p.get().getId());
        assertNotNull(p.get().getProductList());
    }

    @Test
    @Sql(scripts = {"/db/truncateTableProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/db/createTestDataForGetProducts.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findAllProductsFromProductList() {
        Optional<ProductList> productList = productListRepository.findById(10000L);
        List<Product> products = productRepository.findByProductList(productList.get());
        assertEquals(products.size(), 3);
    }

    /*
    Hibernate: select product0_.product_id as product_1_1_,
                      product0_.product_actual_price as product_2_1_,
                       product0_.product_category as product_3_1_,
                       product0_.product_description as product_4_1_,
                       product0_.product_discount as product_5_1_,
                        product0_.product_name as product_6_1_,
                         product0_.product_price as product_7_1_,
                         product0_.product_list_id as product_8_1_
          from products product0_ where product0_.product_name=?

        select productlis0_.id as id1_0_0_,
               productlis0_.description as descript2_0_0_,
               productlis0_.title as title3_0_0_
               from product_lists productlis0_
               where productlis0_.id= 10000

     */

}
