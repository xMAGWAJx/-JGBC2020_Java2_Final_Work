package lv.javaguru.productlist;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidationRuleInterface;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.*;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.ProductCategory;
import lv.javaguru.productlist.ui.ProductUI;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        ProductDatabase database = new ProductDatabase();

        List<ProductValidationRuleInterface> rules = new ArrayList<>();
        rules.add(new ProductDescriptionValidationRule());
        rules.add(new ProductDiscountLimitValidationRule());
        rules.add(new ProductDiscountValidationRule());
        rules.add(new ProductNameValidationRule());
        rules.add(new ProductPriceValidationRule());
        rules.add(new ProductUniqueNameValidationRule(database));
        ProductValidator productValidator = new ProductValidator(rules);

        ProductCategory productCategory = new ProductCategory();
        ProductService productService = new ProductService(database, productValidator);
        ProductUI ui = new ProductUI(productService);

        ui.executeProgram();
    }
}

