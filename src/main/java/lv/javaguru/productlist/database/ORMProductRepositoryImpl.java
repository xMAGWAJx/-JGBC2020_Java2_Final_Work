package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
@Component
public class ORMProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public List<Product> getProducts() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }

    @Override
    public Optional<Product> findById(long id) {
//        return sessionFactory.getCurrentSession().createCriteria(Product.class);
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<Product> getProductByCategory(ProductCategory category) {
        return null;
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        return Optional.empty();
    }
}
