CREATE DATABASE productlist CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;CREATE DATABASE productlist CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- Main table creation

CREATE TABLE products (
product_id BIGINT NOT NULL AUTO_INCREMENT,
product_name VARCHAR(200) NOT NULL,
product_description VARCHAR(200) NOT NULL,
product_price bigint NOT NULL,
product_discount bigint NOT NULL,
product_category VARCHAR(200) NOT NULL,
product_actual_price bigint NOT NULL,
PRIMARY KEY (product_id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1000;

-- SQL queries

select * from products;

-- Test data entries

INSERT INTO products (product_name, product_description, product_price, product_discount, product_category, product_actual_price) VALUES ('TestEntrie', 'DescriptionTest', 40, 20, 'FRUIT', 32);