package lv.javaguru.productlist.businesslogic.services.deleteproductservice;

import lv.javaguru.productlist.database.InMemoryProductRepositoryImpl;
import lv.javaguru.productlist.database.ProductRepository;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteProductByIdService {

    private ProductRepository database;

    @Autowired
    public DeleteProductByIdService(ProductRepository database) {
        this.database = database;
    }

    public DeleteProductByIdResponse deleteById(int id) {
        Optional<Product> product = database.findById(id);
        if (product == null) {
            return new DeleteProductByIdResponse(false, "Product with id '" + id + "' was not found in database.");
        }
        database.deleteById(id);
        return new DeleteProductByIdResponse(true, null);
    }
}
