package lv.javaguru.productlist.businesslogic.services.getproductservice;

import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

import java.util.Optional;

public class GetProductByIdService {

    private ProductDatabase database;

    public GetProductByIdService(ProductDatabase database) {
        this.database = database;
    }

    public Optional<Product> findById(int id) {
        return database.findById(id);
    }
}
