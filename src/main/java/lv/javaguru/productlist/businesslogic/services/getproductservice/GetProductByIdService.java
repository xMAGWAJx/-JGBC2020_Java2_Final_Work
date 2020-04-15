package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetProductByIdService {

    private ProductRepository database;

    @Autowired
    public GetProductByIdService(ProductRepository database) {
        this.database = database;
    }

    public Optional<Product> findById(int id) {
        return database.findById(id);
    }
}
