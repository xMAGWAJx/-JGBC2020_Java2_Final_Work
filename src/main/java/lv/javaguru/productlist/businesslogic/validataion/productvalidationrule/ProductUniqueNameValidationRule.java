package lv.javaguru.productlist.businesslogic.validataion.productvalidationrule;

import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

import java.util.Optional;

public class ProductUniqueNameValidationRule implements ProductValidationRuleInterface {

    private ProductDatabase database;

    public ProductUniqueNameValidationRule(ProductDatabase database) {
        this.database = database;
    }

    @Override
    public boolean isValid(Product product) {
        Optional<Product> opt = database.findProductByName(product.getName());
        return !opt.isPresent();
    }

    @Override
    public String errorMessage() {
        return "Product name already present in database! Please add unique product name.";
    }
}
