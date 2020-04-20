package lv.javaguru.productlist.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "product_price", precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "product_discount", precision = 19, scale = 4)
    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private Category category;

    @Column(name = "product_actual_price", precision = 19, scale = 4)
    private BigDecimal actualPrice;


    public Product() {

    }

    public Product(String name, String description, BigDecimal price, BigDecimal discount, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.actualPrice = price.subtract(price.multiply(discount).divide(BigDecimal.valueOf(100.00)));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (discount != null ? !discount.equals(product.discount) : product.discount != null) return false;
        if (category != product.category) return false;
        return actualPrice != null ? actualPrice.equals(product.actualPrice) : product.actualPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (actualPrice != null ? actualPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount + "%" +
                ", category=" + category +
                ", resultPrice=" + actualPrice +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;

    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
}
