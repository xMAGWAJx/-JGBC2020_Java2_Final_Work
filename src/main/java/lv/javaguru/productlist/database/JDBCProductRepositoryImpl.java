package lv.javaguru.productlist.database;

import lv.javaguru.productlist.domain.Category;
import lv.javaguru.productlist.domain.Product;
import lv.javaguru.productlist.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class JDBCProductRepositoryImpl implements ProductRepository {

    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${driverClass}" )
    private String driverClass;

    @Value( "${database.user.name}" )
    private String userName;

    @Value( "${database.user.password}" )
    private String password;


    protected Connection getConnection() {
        try{
            return DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into PRODUCTS(product_id, product_name, product_description, product_price, product_discount, product_category, product_actual_price) values(default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setBigDecimal(4, product.getDiscount());
            preparedStatement.setString(5, product.getCategory().name());
            preparedStatement.setBigDecimal(6, product.getActualPrice());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                product.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute addProduct");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from PRODUCTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("product_description"));
                product.setPrice(resultSet.getBigDecimal("product_price"));
                product.setDiscount(resultSet.getBigDecimal("product_discount"));

                String categoryStr = resultSet.getString("product_category").toUpperCase();
                Category category = Category.valueOf(categoryStr);
                product.setCategory(category);

                product.setActualPrice(resultSet.getBigDecimal("product_actual_price"));
                products.add(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting products - getProducts");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct = Optional.empty();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from PRODUCTS where product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("product_description"));
                product.setPrice(resultSet.getBigDecimal("product_price"));
                product.setDiscount(resultSet.getBigDecimal("product_discount"));

                String categoryStr = resultSet.getString("product_category").toUpperCase();
                Category category = Category.valueOf(categoryStr);
                product.setCategory(category);

                product.setActualPrice(resultSet.getBigDecimal("product_actual_price"));

                product.setId(id);
                optionalProduct = Optional.of(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting product by id");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return optionalProduct;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from PRODUCTS where product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int resultSet = preparedStatement.executeUpdate();
            return resultSet == 1;

        } catch (Throwable e) {
            System.out.println("Exception while deleting product by id");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Product> getProductByCategory(ProductCategory category) {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from PRODUCTS where product_category = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("product_description"));
                product.setPrice(resultSet.getBigDecimal("product_price"));
                product.setDiscount(resultSet.getBigDecimal("product_discount"));

                String categoryStr = resultSet.getString("product_category").toUpperCase();
                Category productCategory = Category.valueOf(categoryStr);
                product.setCategory(productCategory);

                product.setActualPrice(resultSet.getBigDecimal("product_actual_price"));
                products.add(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting products - getProductByCategory");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        return Optional.empty();
    }
}
