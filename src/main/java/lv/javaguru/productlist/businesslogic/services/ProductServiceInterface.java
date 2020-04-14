package lv.javaguru.productlist.businesslogic.services;

import lv.javaguru.productlist.businesslogic.services.deleteproductservice.DeleteProductByIdResponse;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getProductList();

    DeleteProductByIdResponse deleteById(int id);

    List<Product> getProductByCategory(ProductCategory category);
}
