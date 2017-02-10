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
    Start_Price DOUBLE UNSIGNED NOT NULL,
    Accept_Price DOUBLE UNSIGNED NOT NULL,
    FOREIGN KEY (Product_ID) REFERENCES Product(Product_ID)
);
ALTER TABLE `Auction` AUTO_INCREMENT = 300;

CREATE TABLE Auction_History
(
    Auction_ID INT PRIMARY KEY,
    Product_ID INT NOT NULL,
    Customer_ID INT,
    Final_Bid DOUBLE UNSIGNED,
    Date_Sold DATETIME,
    Start_Date DATE NOT NULL,
    End_Date DATE NOT NULL,
    Start_Price DOUBLE UNSIGNED NOT NULL,
    Accept_Price DOUBLE UNSIGNED NOT NULL,
    FOREIGN KEY (Product_ID) REFERENCES Product(Product_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

CREATE TABLE Customer_Bid
(
    Bid_ID INT AUTO_INCREMENT PRIMARY KEY,
    Customer_ID INT NOT NULL,
    Auction_ID INT NOT NULL,
    Bid DOUBLE UNSIGNED NOT NULL,
    Bid_Date DATETIME NOT NULL,
    FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);
ALTER TABLE `Customer_Bid` AUTO_INCREMENT = 400;

SELECT * FROM Customer_Bid;

-- Skapa Supplier
INSERT INTO Supplier(Company_Name, Address, Zip_Code, City, Phone_Number, Email)VALUES('Bus & Båg', 'Frejgatan 43', '11384', 'Stockholm', '070-6785423', 'busbag@hotmail.com');
INSERT INTO Supplier(Company_Name, Address, Zip_Code, City, Phone_Number, Email)VALUES('FiskeRederiet', 'Avenyn 3', '41821', 'Göteborg', '076-2598341', 'luktafisk@gmail.com');
INSERT INTO Supplier(Company_Name, Address, Zip_Code, City, Phone_Number, Email)VALUES('Myndigheten', 'Regeringsgatan 69', '11166', 'Stockholm', '08-567962', 'myndig@myndigheten.com');
INSERT INTO Supplier(Company_Name, Address, Zip_Code, City, Phone_Number, Email)VALUES('BakMums', 'bullgatan 31', '84223', 'Sundsvall', '070-8526688', 'bak@mums.se');
INSERT INTO Supplier(Company_Name, Address, Zip_Code, City, Phone_Number, Email)VALUES('StålAB', 'Järngatan 82', '80124', 'Gävle', '026-526341', 'stal@hotmail.com');

-- Skapa Product
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info)VALUES('1', 'Platt-TV', 0.10, '2017-01-09 15:05:24', 'Fin platt-TV, inget kvitto finns.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info)VALUES('2', 'Fiskebåt', 0.10, '2017-01-12 11:07:51', 'Fin båt men flyter tyvärr inte.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info)VALUES('3', 'Fax', 0.10, '2017-02-09 09:44:01', 'En av Sveriges största och snabbaste faxmaskiner.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info)VALUES('4', 'Ugn', 0.10, '2017-01-29 20:04:51', '10-årig ugn som var med och vann Bak-SM 2011.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info)VALUES('5', 'Svetsmaskin', 0.10, '2017-02-01 06:32:58', 'Sjukt bra svets, säljer för att ha råd till frimafesten i mars.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info) VALUES ('1', 'Ståltunna', 0.05, '2016-01-01', 'En rustik tunna i plåt.');
INSERT INTO Product(Supplier_ID, Product_Name, Commission, Entry_Date, Info) VALUES ('1', 'Fiskespö', 0.10, '2016-01-02', 'Fint gammalt fiskespö med inbyggd fiskelycka.');
INSERT INTO Product VALUES (95, 1, 'Våningsäng', 0.10, '2016-01-01', 'Fin våningsäng från år 1916' );
INSERT INTO Product VALUES (96, 2, 'GungStol', 0.10, '2016-03-05', 'Stol med inbygd gungfunktion' );
INSERT INTO Product VALUES (97, 1, 'Amiga600', 0.10, '2016-01-16', 'Det senaste inom dator från 89' );
INSERT INTO Product VALUES (94, 2, 'Snus', 0.10, '2016-01-27', 'En halvfull dosa Göteborgs Rape (White Large)' );

-- Skapa Auction
INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)VALUES(100, '2017-02-20', '2017-03-20', '4000', '7000');
INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)VALUES(101, '2017-02-24', '2017-03-24', '75000', '89999');
INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)VALUES(102, '2017-01-11', '2017-02-11', '25000', '27000');
INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)VALUES(103, '2017-01-28', '2017-02-28', '6000', '15000');
INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)VALUES(104, '2017-02-17', '2017-03-17', '3000', '3500');


-- Skapa Customer
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Bruce', 'Wayne', '0707572626', 'imNotBatman@forReal.com', 'Wayne Mansion', 'Gotham', '66666');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Peter', 'Parker', '0721112223', 'Mj@ever.com', 'Queens Av7', 'New York', '95423');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Clark', 'Kent', '0707554845', 'Cryptonite@hate.com', 'Crystal Palace 1', 'Metropolis', '74554');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Charles', 'Xavier', '0744444445', 'ProffX@Xman.com', 'X-Mansion', 'Wachington', '15452');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Lex', 'Luthor', '0725416852', 'Ihatesuper@man.com', 'LexStreet', 'Metropolis', '22154');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code) VALUES ('Tor', 'Odensson', '0707522222', 'Mjolnir@hammer.com', 'VikingLane4', 'Valhalla', '78723');

-- Skapa Customer_Bid
INSERT INTO Customer_Bid(Customer_ID, Auction_ID, Bid, Bid_Date)VALUES(200, 302, '25500', '2017-02-04 19:05:37');
INSERT INTO Customer_Bid(Customer_ID, Auction_ID, Bid, Bid_Date)VALUES(200, 303, '6500', '2017-02-03 23:24:01');
INSERT INTO Customer_Bid(Customer_ID, Auction_ID, Bid, Bid_Date)VALUES(200, 302, '26000', '2017-02-09 07:45:05');
INSERT INTO Customer_Bid(Customer_ID, Auction_ID, Bid, Bid_Date)VALUES(200, 303, '8000', '2017-02-09 14:36:24');


-- Skapa Auction_History
INSERT INTO Auction_History(Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price) VALUES (98, 105, 200, 100, '2016-02-01 09:15:17', '2016-01-05', '2016-02-01', 25, 100);
INSERT INTO Auction_History(Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price) VALUES (99, 106, 200, 250, '2016-02-05 13:00:00', '2016-01-22', '2016-02-22', 100, 250);
INSERT INTO auction_history VALUES (290, 95, 203, 2054, '2016-01-15 14:15:06', '2016-01-12', '2016-01-18', 1000, 2500 );
INSERT INTO auction_history VALUES (291, 96, 204, 527, '2016-03-27 20:28:29', '2016-03-06', '2016-03-30', 500, 600 );
INSERT INTO auction_history VALUES (292, 97, 204, 10000, '2016-01-25 23:15:15', '2016-01-18', '2016-01-26', 5000, 15000 );
INSERT INTO auction_history VALUES (293, 94, 203, 25, '2016-03-05 17:19:48', '2016-03-01', '2016-03-06', 20, 35 );

-- Fråga 5
DROP PROCEDURE IF EXISTS Ended_Auctions;
DELIMITER //

CREATE PROCEDURE Ended_Auctions(IN mStart_Date DATE, IN mEnd_Date DATE)
    BEGIN

        SELECT
            Auction_History.Start_Date,
            Auction_History.End_Date,
            Auction_History.Date_Sold,
            Auction_History.Final_Bid,
            SUM(Auction_History.Final_Bid * (Product.Commission / 100)) AS Comission
        FROM Auction_History
            INNER JOIN Product ON Auction_History.Product_ID = Product.Product_ID
        WHERE Auction_History.Start_Date AND Auction_History.End_Date BETWEEN mStart_Date AND mEnd_Date
        GROUP BY Start_Date, End_Date, Date_Sold, Final_Bid;

    END //
DELIMITER ;

CALL Ended_Auctions('2016-01-01', '2016-12-12');