package lv.javaguru.productlist.domain;

import java.util.Arrays;
import java.util.List;

public class ProductCategory {

    public static Category getCategory(int category) {
        Category result;
        if (category == 1) {
            result = Category.FRUIT;
        } else if (category == 2) {
            result = Category.VEGETABLES;
        } else if (category == 3) {
            result = Category.DRINKS;
        } else {
            result = Category.OTHER_CATEGORY;
        }
        return result;
    }

    public static void showAllCategories() {
        List<Category> categories = Arrays.asList(Category.values());
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + 1 + " - " + categories.get(i));
        }
    }

}
