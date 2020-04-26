CREATE DATABASE productlist CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Main table creation

CREATE TABLE product_lists (
id BIGINT NOT NULL AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
description VARCHAR(200) NOT NULL,
PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1000;


CREATE TABLE products (
product_id BIGINT NOT NULL AUTO_INCREMENT,

product_list_id BIGINT NOT NULL,

product_name VARCHAR(200) NOT NULL,
product_description VARCHAR(200) NOT NULL,
product_price decimal NOT NULL,
product_discount decimal NOT NULL,
product_category VARCHAR(200) NOT NULL,
product_actual_price decimal NOT NULL,

PRIMARY KEY (product_id),

FOREIGN KEY (product_list_id) REFERENCES product_lists(id)

)
ENGINE = InnoDB
AUTO_INCREMENT = 1000;

-- SQL queries

select * from products;

select * from product_lists;

-- Truncate and drop statements

DROP TABLE products;

TRUNCATE TABLE products;
