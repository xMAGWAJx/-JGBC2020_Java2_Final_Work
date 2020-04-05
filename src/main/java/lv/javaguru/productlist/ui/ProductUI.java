package lv.javaguru.productlist.ui;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.businesslogic.services.addservice.AddProductResponse;
import lv.javaguru.productlist.businesslogic.services.deleteproductservice.DeleteProductByIdResponse;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductUI {

    private ProductService productService;

    public ProductUI(ProductService productService) {
        this.productService = productService;
    }

    public void executeProgram() {

        while (true) {
            // print menu
            System.out.println("Program menu:");
            System.out.println("1. Add new product");
            System.out.println("2. Show product list");
            System.out.println("3. Get product by id");
            System.out.println("4. Get product by category -- NOT DONE");
            System.out.println("5. Delete product by id");
            System.out.println("6. Exit");

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
                // Get product by id
                System.out.println("Enter product id: ");
                int productId = sc.nextInt();
                Optional<Product> resultProductById = productService.findById(productId);

                if (resultProductById == null) {
                    System.out.println("Product with provided id does not exist in the database: " + productId);
                } else {
                    System.out.println(resultProductById);
                }
            }

            if (userChoice == 4) {
                // Get product by category
                ProductCategory.showAllCategories();
                System.out.println("Enter product category: ");
//                int selectProductCategory = sc.nextInt();
//                List<Product> productsByCategory = productService.getProductByCategory(selectProductCategory);
//                productsByCategory.forEach(product -> System.out.println(product.toString()));

            }

            if (userChoice == 5) {
                // Delete product by id
                System.out.println("Enter product id that should be deleted: ");
                int productId = sc.nextInt();
                DeleteProductByIdResponse productDeletionResponse = productService.deleteById(productId);

                if (productDeletionResponse.isSuccess()) {
                    System.out.println("Operation successful!");
                    System.out.println("Product with id = '" + productId + "' was deleted.");
                } else {
                    System.out.println("Operation failed!");
                    System.out.println("Error message: " + productDeletionResponse.getErrorMessages());
                }

            }

            if (userChoice == 6) {
                // exit from program
                System.out.println("Goodbye");
                break;
            }

            System.out.println();

            // exit condition
        }

    }

}
