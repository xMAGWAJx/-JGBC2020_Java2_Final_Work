package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
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

    //@Autowired
    private SessionFactory sessionFactory = null;


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
    public List<Product> getProductByCategory(Category category) {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("category", category)).list();
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("name", productName)).uniqueResult();
        return Optional.ofNullable(product);
    }
}
