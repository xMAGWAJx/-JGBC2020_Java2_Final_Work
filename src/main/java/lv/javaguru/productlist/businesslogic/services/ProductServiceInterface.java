package lv.javaguru.productlist.businesslogic.services;

import lv.javaguru.productlist.businesslogic.services.addservice.AddProductResponse;
import lv.javaguru.productlist.businesslogic.services.deleteproductservice.DeleteProductByIdResponse;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;

public interface ProductServiceInterface {

    AddProductResponse addProduct(Product product);

    List<Product> getProductList();

    Product findById(int id);

    DeleteProductByIdResponse deleteById(int id);

    List<Product> getProductByCategory(ProductCategory category);
}
