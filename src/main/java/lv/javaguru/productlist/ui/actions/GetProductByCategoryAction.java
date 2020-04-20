//package lv.javaguru.productlist.ui.actions;
//
//import lv.javaguru.productlist.businesslogic.services.getproductservice.GetProductByCategoryService;
//import lv.javaguru.productlist.domain.Product;
//import lv.javaguru.productlist.domain.ProductCategory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Scanner;
//
//@Component
//public class GetProductByCategoryAction implements UIAction{
//
//  private GetProductByCategoryService productService;
//
//  @Autowired
//  public GetProductByCategoryAction(GetProductByCategoryService productService) {
//    this.productService = productService;
//  }
//
//  @Override
//  public void execute() {
//    System.out.println("Enter product category: ");
//    Scanner sc = new Scanner(System.in);
//    int productCategory = sc.nextInt();
//    List<Product> resultProductByCategory = productService.getProductByCategory(ProductCategory.getCategory(productCategory));
//
//    if (resultProductByCategory == null) {
//      System.out.println("Product with provided category does not exist in hte database: " + productCategory);
//    } else {
//      System.out.println(resultProductByCategory);
//    }
//  }
//
//  @Override
//  public int getMenuNumber() {
//    return 4;
//  }
//}
