package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public Optional<Product> findById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("id", id)).uniqueResult();
        if (product == null) {
            System.out.println("Product with id = '" + id + "' was not found in database.");
        }
        return Optional.ofNullable(product);
    }

    @Override
    public boolean deleteById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("id", id)).uniqueResult();
        if (product == null) {
            System.out.println("Product with id = '" + id + "' was not found in database.");
            return false;
        } else {
            sessionFactory.getCurrentSession().delete(product);
            return true;
        }
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
