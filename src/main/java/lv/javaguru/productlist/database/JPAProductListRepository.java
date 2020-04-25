package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.ProductList;
import org.springframework.data.repository.CrudRepository;

public interface JPAProductListRepository
        extends CrudRepository<ProductList, Long>  {


}
