package lv.javaguru.productlist.businesslogic.validataion;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductDescriptionValidationRuleInterface;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductDiscountValidationRuleInterface;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductNameValidationRuleInterface;
import lv.javaguru.productlist.businesslogic.validataion.productvalidationrule.ProductPriceValidationRuleInterface;
import lv.javaguru.productlist.domain.Product;

import static java.util.stream.Collectors.toList;

public class ProductValidator {

    private List<ProductValidationRuleInterface> validationRules;

    public ProductValidator() {
        validationRules = new ArrayList<>();
        validationRules.add(new ProductNameValidationRuleInterface());
        validationRules.add(new ProductPriceValidationRuleInterface());
        validationRules.add(new ProductDiscountValidationRuleInterface());
        validationRules.add(new ProductDescriptionValidationRuleInterface());
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
