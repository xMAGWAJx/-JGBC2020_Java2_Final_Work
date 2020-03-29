package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.domain.Product;

public class ProductDiscountValidationRule implements ProductValidationRule {

    @Override
    public boolean isValid(Product product) {
        return product.getDiscount() != null && !product.getDiscount().equals("");
    }

    @Override
    public String errorMessage() {
        return "Empty product discount";
    }
}
