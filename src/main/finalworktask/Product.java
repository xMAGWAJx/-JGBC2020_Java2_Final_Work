package main.finalworktask;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private BigDecimal price;
    private productCategory category;
    private BigDecimal discount;
    private String description;


    public Product(String name, BigDecimal price, productCategory category, BigDecimal discount, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public productCategory getCategory() {
        return category;
    }

    public void setCategory(productCategory category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum productCategory {
        VEGETABLE {
            @Override
            public String toString() {
                return "Vegetable";
            }
        },

        FRUIT {
            @Override
            public String toString() {
                return "Fruit";
            }
        },

        DRINKS {
            @Override
            public String toString() {
                return "Drinks";
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                category == product.category &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, discount, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }
}
