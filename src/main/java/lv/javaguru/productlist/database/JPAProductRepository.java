package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JPAProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

}
