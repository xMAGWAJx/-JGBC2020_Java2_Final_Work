package lv.javaguru.productlist.ui;

import java.util.List;
import java.util.Scanner;

import lv.javaguru.productlist.businesslogic.AddProductResponse;
import lv.javaguru.productlist.businesslogic.ProductService;
import lv.javaguru.productlist.businesslogic.validataion.ProductValidator;
import lv.javaguru.productlist.database.ProductDatabase;
import lv.javaguru.productlist.domain.Product;

public class ProductUI {

    public static void main(String[] args) {
        ProductDatabase database = new ProductDatabase();
        ProductValidator productValidator = new ProductValidator();
        ProductService productService = new ProductService(database, productValidator);

        while (true) {
            // print menu
            System.out.println("Program menu:");
            System.out.println("1. Add new product");
            System.out.println("2. Show product list");
            System.out.println("3. Exit");

            // get user choice
            Scanner sc = new Scanner(System.in);
            System.out.println("Select option: ");
            int userChoice = Integer.parseInt(sc.nextLine());

            // execute user choice
            if (userChoice == 1) {
                // add new product
                System.out.println("Enter product name:");
                String productName = sc.nextLine();
                System.out.println("Enter product description:");
                String productDescription = sc.nextLine();
                Product product = new Product(productName, productDescription);
                // invoke BL
                AddProductResponse response = productService.addProduct(product);
                if (response.isSuccess()) {
                    System.out.println("Operation successful!");
                } else {
                    System.out.println("Operation failed!");
                    List<String> errors = response.getErrorMessages();
                    errors.forEach(error ->
                            System.out.println("Error message: " + error));
                }
            }

            if (userChoice == 2) {
                // Show product list
                List<Product> products = productService.getProductList();
                products.forEach(product -> System.out.println(product.toString()));
            }

            if (userChoice == 3) {
                // exit from program
                System.out.println("Googby!");
                break;
            }

            System.out.println();

            // exit condition
        }

    }

}
