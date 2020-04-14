package lv.javaguru.productlist.businesslogic.services;

import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getProductList();

    List<Product> getProductByCategory(ProductCategory category);
}
