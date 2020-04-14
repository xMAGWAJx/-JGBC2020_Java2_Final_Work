package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetProductByIdService {

    private ProductDatabase database;

    @Autowired
    public GetProductByIdService(ProductDatabase database) {
        this.database = database;
    }

    public Optional<Product> findById(int id) {
        return database.findById(id);
    }
}
