package lv.javaguru.productlist.businesslogic.services;

import java.util.List;

import lv.javaguru.productlist.businesslogic.services.addservice.AddProductResponse;
import lv.javaguru.productlist.businesslogic.services.addservice.AddProductService;
import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductByIdService;
import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductService;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

public class ProductService implements ProductServiceInterface{

    private ProductValidator productValidator;
    private ProductDatabase database;

    public ProductService(ProductDatabase database, ProductValidator productValidator) {
        this.database = database;
        this.productValidator = productValidator;
    }

    @Override
    public AddProductResponse addProduct(Product product) {
        AddProductService service = new AddProductService(database, productValidator);
        return service.addProduct(product);
    }

    @Override
    public List<Product> getProductList() {
        return new GetProductService(database).getProductList();
    }

    @Override
    public Product findById(int id) {
        return new GetProductByIdService(database).findById(id);
    }
}
