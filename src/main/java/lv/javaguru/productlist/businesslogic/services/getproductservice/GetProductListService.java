package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.JPAProductRepository;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetProductListService {

    private JPAProductRepository productRepository;

    @Autowired
    public GetProductListService(JPAProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getProductList() {
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
