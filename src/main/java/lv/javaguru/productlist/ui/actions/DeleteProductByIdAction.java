package lv.javaguru.productlist.ui.actions;

import lv.javaguru.productlist.businesslogic.services.deleteproductservice.DeleteProductByIdResponse;
import lv.javaguru.productlist.businesslogic.services.deleteproductservice.DeleteProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteProductByIdAction implements UIAction {

    private DeleteProductByIdService productService;

    @Autowired
    public DeleteProductByIdAction(DeleteProductByIdService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product id that should be deleted: ");
        Scanner sc = new Scanner(System.in);
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

    @Override
    public int getMenuNumber() {
        return 5;
    }

}
