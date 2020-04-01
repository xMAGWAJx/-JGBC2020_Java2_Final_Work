package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductDescriptionValidationRule;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductDiscountValidationRule;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductNameValidationRule;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductPriceValidationRule;
import lv.javaguru.productlist.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductValidator {

    private List<ProductValidationRuleInterface> validationRules;

    public ProductValidator() {
        validationRules = new ArrayList<>();
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductDescriptionValidationRule());
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
