package lv.javaguru.productlist.ui.actions;

import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductListService;
import lv.javaguru.productlist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowProductListAction implements UIAction {

    private GetProductListService productService;

    @Autowired
    public ShowProductListAction(GetProductListService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Product> products = productService.getProductList();
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Override
    public int getMenuNumber() {
        return 2;
    }

}
