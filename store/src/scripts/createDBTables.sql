USE db_store;

SHOW TABLES;
DROP TABLE IF EXISTS products, customers, carts, orders;
SHOW TABLES;
CREATE TABLE customers(id SERIAL, fname VARCHAR(255), lname VARCHAR(255), 
		username VARCHAR(255),email VARCHAR(255), UNIQUE(username));

CREATE TABLE products(itemId SERIAL, name VARCHAR(255), msrp DECIMAL(6,2), salePrice DECIMAL(8,2), 
	upc INT, shortDescription VARCHAR(255), brandName VARCHAR(255), 
	size VARCHAR(255), color VARCHAR(255), gender VARCHAR(255), UNIQUE(itemId));

CREATE TABLE carts(cartId INT, username VARCHAR(255), itemId INT, count INT, UNIQUE(cartId, username, itemId));

CREATE TABLE orders(orderId INT, username VARCHAR(255), itemId INT, count INT, UNIQUE(orderId, username, itemId));

SHOW TABLES;