package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetProductByCategoryService {

    private JPAProductRepository productRepository;

    @Autowired
    public GetProductByCategoryService(JPAProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}
