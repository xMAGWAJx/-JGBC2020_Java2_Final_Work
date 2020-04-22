package lv.javaguru.productlist.businesslogic.services.deleteproductservice;

import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteProductByIdService {

    private JPAProductRepository productRepository;

    @Autowired
    public DeleteProductByIdService(JPAProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public DeleteProductByIdResponse deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product == null) {
            return new DeleteProductByIdResponse(false, "Product with id '" + id + "' was not found in database.");
        }
        productRepository.deleteById(id);
        return new DeleteProductByIdResponse(true, null);
    }
}
