package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.*;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.database.ProductDatabaseInterface;
import lv.javaguru.productlist.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductValidator {

    private List<ProductValidationRuleInterface> validationRules;
    private ProductDatabaseInterface database;

    public ProductValidator(ProductDatabase database) {
        this.database = database;
        validationRules = new ArrayList<>();
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductDescriptionValidationRule());
        validationRules.add(new ProductUniqueNameValidationRule(database));
        validationRules.add(new ProductDiscountLimitValidationRule());
    }

    public ProductValidationResponse validate(Product product) {
        List<String> errorMessages =
                validationRules.stream()
                        .filter(rule -> !rule.isValid(product))
                        .map(rule -> rule.errorMessage())
                        .collect(toList());
        if (errorMessages.isEmpty()) {
            return new ProductValidationResponse(true, null);
        } else {
            return new ProductValidationResponse(false, errorMessages);
        }
    }
}
