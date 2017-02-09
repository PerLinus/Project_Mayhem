DROP DATABASE IF EXISTS Project_Mayhem;
CREATE DATABASE Project_Mayhem;
USE Project_Mayhem;

CREATE TABLE Supplier
(
	Supplier_ID INT AUTO_INCREMENT PRIMARY KEY,
	Company_Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
    Zip_Code CHAR(5) NOT NULL,
    City VARCHAR(20) NOT NULL,
    Phone_Number VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL
);

CREATE TABLE Product
(
	Product_ID INT AUTO_INCREMENT PRIMARY KEY,
    Supplier_ID INT NOT NULL,
    Product_Name VARCHAR(100) NOT NULL,
    Start_Price DOUBLE UNSIGNED NOT NULL,
    Accept_Price DOUBLE UNSIGNED NOT NULL,
    Commission DOUBLE NOT NULL,
    Entry_Date DATETIME NOT NULL,
    Info TEXT,
    FOREIGN KEY (Supplier_ID) REFERENCES Supplier(Supplier_ID)
);
ALTER TABLE `Product` AUTO_INCREMENT = 100;

CREATE TABLE Customer
(
	Customer_ID INT AUTO_INCREMENT PRIMARY KEY,
    First_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    Phone_Number VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Zip_Code CHAR(5) NOT NULL
);
ALTER TABLE `Customer` AUTO_INCREMENT = 200;

CREATE TABLE Auction
(
	Auction_ID INT AUTO_INCREMENT PRIMARY KEY,
    Product_ID INT NOT NULL,
    Start_Date DATE NOT NULL,
    End_Date DATE NOT NULL,
    FOREIGN KEY (Product_ID) REFERENCES Product(Product_ID)
);
ALTER TABLE `Auction` AUTO_INCREMENT = 300;

CREATE TABLE Auction_History
(
	Auction_ID INT PRIMARY KEY,
    Product_ID INT NOT NULL,
    Customer_ID INT NOT NULL,
    Final_Bid DOUBLE UNSIGNED NOT NULL,
    Date_Sold DATE NOT NULL,
    FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID),
    FOREIGN KEY (Product_ID) REFERENCES Product(Product_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

CREATE TABLE Customer_Bid
(
	Bid_ID INT AUTO_INCREMENT PRIMARY KEY,
    Customer_ID INT NOT NULL,
    Product_ID INT NOT NULL,
    Auction_ID INT NOT NULL,
    Bid DOUBLE UNSIGNED NOT NULL,
    Bid_Date DATETIME NOT NULL,
    FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID),
    FOREIGN KEY (Product_ID) REFERENCES Product(Product_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);
ALTER TABLE `Customer_Bid` AUTO_INCREMENT = 400;
    