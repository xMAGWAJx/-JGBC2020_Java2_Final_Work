package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class GetProductByIdService {

    private JPAProductRepository productRepository;

    @Autowired
    public GetProductByIdService(JPAProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
