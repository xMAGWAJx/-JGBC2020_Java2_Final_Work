package lv.javaguru.productlist.ui.actions;

import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductByIdService;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class GetProductByIdAction implements UIAction {

    private GetProductByIdService productService;

    @Autowired
    public GetProductByIdAction(GetProductByIdService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product id: ");
        Scanner sc = new Scanner(System.in);
        int productId = sc.nextInt();
        Optional<Product> resultProductById = productService.findById(productId);

        if (resultProductById == null) {
            System.out.println("Product with provided id does not exist in the database: " + productId);
        } else {
            System.out.println(resultProductById);
        }
    }

    @Override
    public int getMenuNumber() {
        return 3;
    }

}
