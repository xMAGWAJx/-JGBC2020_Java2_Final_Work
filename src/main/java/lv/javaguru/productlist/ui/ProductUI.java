package lv.javaguru.productlist.ui;

import lv.javaguru.productlist.businesslogic.services.ProductService;
import lv.javaguru.productlist.ui.actions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ProductUI {

    private List<UIAction> uiActions;

    @Autowired
    public ProductUI(List<UIAction> uiActions) {
        this.uiActions = uiActions;
    }

    private void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add new product");
        System.out.println("2. Show product list");
        System.out.println("3. Get product by id");
        //System.out.println("4. Get product by category -- NOT DONE");
        System.out.println("5. Delete product by id");
        System.out.println("6. Exit");
    }

    private int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select option: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void executeProgram() {
        while (true) {
            printMenu();
            int userChoice = getUserChoice();

            Optional<UIAction> actionOpt = uiActions.stream()
                    .filter(uiAction -> uiAction.getMenuNumber() == userChoice)
                    .findFirst();
            if (actionOpt.isPresent()) {
                UIAction action = actionOpt.get();
                action.execute();
            } else {
                System.out.println("Your choice is incorrect, please try again!");
            }

            System.out.println();
        }

    }

}
