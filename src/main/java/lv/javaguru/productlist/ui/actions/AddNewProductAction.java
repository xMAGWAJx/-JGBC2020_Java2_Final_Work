package lv.javaguru.productlist.ui.actions;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.businesslogic.services.addservice.AddProductResponse;
import lv.javaguru.productlist.businesslogic.services.addservice.AddProductService;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class AddNewProductAction implements UIAction {

    private AddProductService addProductService;

    @Autowired
    public AddNewProductAction(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product name:");
        Scanner sc = new Scanner(System.in);
        String productName = sc.nextLine();
        System.out.println("Enter product description:");
        String productDescription = sc.nextLine();
        System.out.println("Enter product price (Grater that 0):");
        BigDecimal productPrice = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("Enter product discount (Values between 0 - 100):");
        BigDecimal productDiscount = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("Chose one category from list below (From 1 - 4)");
        ProductCategory.showAllCategories();
        int category = sc.nextInt();
        Product product = new Product(productName, productDescription, productPrice, productDiscount, ProductCategory.getCategory(category));
        // invoke BL
        AddProductResponse response = addProductService.addProduct(product);
        if (response.isSuccess()) {
            System.out.println("Operation successful!");
        } else {
            System.out.println("Operation failed!");
            List<String> errors = response.getErrorMessages();
            errors.forEach(error ->
                    System.out.println("Error message: " + error));
        }
    }

    @Override
    public int getMenuNumber() {
        return 1;
    }

}
