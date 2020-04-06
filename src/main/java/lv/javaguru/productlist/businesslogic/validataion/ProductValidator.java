package lv.javaguru.productlist.businesslogic.validataion;

import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductValidator {

    private List<ProductValidationRuleInterface> validationRules;

    @Autowired
    public ProductValidator(List<ProductValidationRuleInterface> rules) {
        validationRules = rules;
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
