package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.domain.Product;

public interface ProductValidationRuleInterface {

    boolean isValid(Product product);

    String errorMessage();

}
