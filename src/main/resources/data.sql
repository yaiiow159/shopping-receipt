
DROP DATABASE IF EXISTS shopping_receipt;
CREATE DATABASE shopping_receipt;
USE shopping_receipt;

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_tax_exempt BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO category (name, is_tax_exempt) VALUES('FOOD', TRUE);
INSERT INTO category (name, is_tax_exempt) VALUES('CLOTHING',FALSE);

DROP TABLE IF EXISTS location;
CREATE TABLE location (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  tax_rate DECIMAL(5, 4) NOT NULL
);

INSERT INTO location (name, tax_rate) VALUES('CALIFORNIA', 9.75);
INSERT INTO location (name, tax_rate) VALUES('NEW YORK', 8.75);
INSERT INTO location (name, tax_rate) VALUES('TEXAS', 7.75);
INSERT INTO location (name, tax_rate) VALUES('FLORIDA', 6.75);
INSERT INTO location (name, tax_rate) VALUES('HAWAII', 5.75);
INSERT INTO location (name, tax_rate) VALUES('ALASKA', 4.75);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     quantity INT NOT NULL,
     category_id BIGINT NOT NULL,
     location_id BIGINT NOT NULL,
     is_tax_exempt BOOLEAN NOT NULL DEFAULT FALSE,
     FOREIGN KEY (category_id) REFERENCES category(id),
     FOREIGN KEY (location_id) REFERENCES location(id)
);

INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('APPLE', 0.99, 10, 1, 1, FALSE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('BANANA', 0.75, 20, 1, 1, TRUE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('T-SHIRT', 19.99, 5, 2, 2, FALSE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('SWEATER', 29.99, 3, 2, 2, FALSE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('JACKET', 49.99, 1, 2, 2, TRUE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('PANTS', 39.99, 2, 2, 2, FALSE);
INSERT INTO product (name, price, quantity, category_id, location_id, is_tax_exempt) VALUES('SANDAL', 9.99, 50, 2, 2, TRUE);
