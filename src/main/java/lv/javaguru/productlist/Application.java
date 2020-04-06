package lv.javaguru.productlist;

import lv.javaguru.productlist.ui.ProductUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ProductUI ui = applicationContext.getBean(ProductUI.class);
        ui.executeProgram();
    }
}

