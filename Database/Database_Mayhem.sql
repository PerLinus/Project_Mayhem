DROP DATABASE IF EXISTS Project_Mayhem;
CREATE DATABASE Project_Mayhem;
USE Project_Mayhem;

CREATE TABLE Supplier
(
  Supplier_ID  INT AUTO_INCREMENT PRIMARY KEY,
  Company_Name VARCHAR(50) NOT NULL,
  Address      VARCHAR(50) NOT NULL,
  Zip_Code     CHAR(5)     NOT NULL,
  City         VARCHAR(20) NOT NULL,
  Phone_Number VARCHAR(20) NOT NULL,
  Email        VARCHAR(50) NOT NULL
);

CREATE TABLE Product
(
  Product_ID   INT AUTO_INCREMENT PRIMARY KEY,
  Supplier_ID  INT                                NOT NULL,
  Product_Name VARCHAR(100)                       NOT NULL,
  Commission   DOUBLE                             NOT NULL,
  Entry_Date   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  Info         TEXT,
  FOREIGN KEY (Supplier_ID) REFERENCES Supplier (Supplier_ID)
);
ALTER TABLE `Product`
  AUTO_INCREMENT = 100;

CREATE TABLE Customer
(
  Customer_ID  INT AUTO_INCREMENT PRIMARY KEY,
  First_Name   VARCHAR(50) NOT NULL,
  Last_Name    VARCHAR(50) NOT NULL,
  Phone_Number VARCHAR(20) NOT NULL,
  Email        VARCHAR(50) NOT NULL,
  Address      VARCHAR(50) NOT NULL,
  City         VARCHAR(50) NOT NULL,
  Zip_Code     CHAR(5)     NOT NULL
);
ALTER TABLE `Customer`
  AUTO_INCREMENT = 200;

CREATE TABLE Auction
(
  Auction_ID   INT AUTO_INCREMENT PRIMARY KEY,
  Product_ID   INT             NOT NULL,
  Start_Date   DATE            NOT NULL,
  End_Date     DATE            NOT NULL,
  Start_Price  DOUBLE UNSIGNED NOT NULL,
  Accept_Price DOUBLE UNSIGNED NOT NULL,
  FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID)
    ON DELETE CASCADE
);
ALTER TABLE `Auction`
  AUTO_INCREMENT = 300;

CREATE TABLE Auction_History
(
  Auction_ID   INT PRIMARY KEY,
  Product_ID   INT             NOT NULL,
  Customer_ID  INT,
  Final_Bid    DOUBLE UNSIGNED,
  Date_Sold    DATETIME,
  Start_Date   DATE            NOT NULL,
  End_Date     DATE            NOT NULL,
  Start_Price  DOUBLE UNSIGNED NOT NULL,
  Accept_Price DOUBLE UNSIGNED NOT NULL,
  FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID),
  FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
);

CREATE TABLE Customer_Bid
(
  Bid_ID      INT AUTO_INCREMENT PRIMARY KEY,
  Customer_ID INT                                NOT NULL,
  Auction_ID  INT                                NOT NULL,
  Bid         DOUBLE UNSIGNED                    NOT NULL,
  Bid_Date    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (Auction_ID) REFERENCES Auction (Auction_ID) ON DELETE CASCADE,
  FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
  ON DELETE CASCADE
);
ALTER TABLE `Customer_Bid`
  AUTO_INCREMENT = 400;

CREATE TABLE LogIn
(
  UserName     CHAR(50) PRIMARY KEY,
  Password     CHAR(50)           NOT NULL,
  Admin_Access BOOL DEFAULT FALSE NOT NULL
);

CREATE VIEW Unsold_Products_View
AS
  SELECT
    Auction_History.Auction_ID,
    Auction_History.Product_ID,
    Auction_History.Start_Date,
    Auction_History.End_Date,
    Auction_History.Start_Price
  FROM Auction_History
  WHERE Auction_History.Final_Bid IS NULL;

-- Skapa Supplier
INSERT INTO Supplier (Company_Name, Address, Zip_Code, City, Phone_Number, Email)
VALUES ('Bus & Båg', 'Frejgatan 43', '11384', 'Stockholm', '070-6785423', 'busbag@hotmail.com');
INSERT INTO Supplier (Company_Name, Address, Zip_Code, City, Phone_Number, Email)
VALUES ('FiskeRederiet', 'Avenyn 3', '41821', 'Göteborg', '076-2598341', 'luktafisk@gmail.com');
INSERT INTO Supplier (Company_Name, Address, Zip_Code, City, Phone_Number, Email)
VALUES ('Myndigheten', 'Regeringsgatan 69', '11166', 'Stockholm', '08-567962', 'myndig@myndigheten.com');
INSERT INTO Supplier (Company_Name, Address, Zip_Code, City, Phone_Number, Email)
VALUES ('BakMums', 'bullgatan 31', '84223', 'Sundsvall', '070-8526688', 'bak@mums.se');
INSERT INTO Supplier (Company_Name, Address, Zip_Code, City, Phone_Number, Email)
VALUES ('StålAB', 'Järngatan 82', '80124', 'Gävle', '026-526341', 'stal@hotmail.com');

-- Skapa Product
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('1', 'Platt-TV', 0.10, '2017-01-09 15:05:24', 'Fin platt-TV, inget kvitto finns.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('2', 'Fiskebåt', 0.10, '2017-01-12 11:07:51', 'Fin båt men flyter tyvärr inte.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('3', 'Fax', 0.10, '2017-02-09 09:44:01', 'En av Sveriges största och snabbaste faxmaskiner.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('4', 'Ugn', 0.10, '2017-01-29 20:04:51', '10-årig ugn som var med och vann Bak-SM 2011.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info) VALUES
  ('5', 'Svetsmaskin', 0.10, '2017-02-01 06:32:58', 'Sjukt bra svets, säljer för att ha råd till frimafesten i mars.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('1', 'Ståltunna', 0.05, '2016-01-01', 'En rustik tunna i plåt.');
INSERT INTO Product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
VALUES ('1', 'Fiskespö', 0.10, '2016-01-02', 'Fint gammalt fiskespö med inbyggd fiskelycka.');
INSERT INTO Product VALUES (95, 1, 'Våningsäng', 0.10, '2016-01-01', 'Fin våningsäng från år 1916');
INSERT INTO Product VALUES (96, 2, 'GungStol', 0.10, '2016-03-05', 'Stol med inbygd gungfunktion');
INSERT INTO Product VALUES (97, 1, 'Amiga600', 0.10, '2016-01-16', 'Det senaste inom dator från 89');
INSERT INTO Product VALUES (94, 2, 'Snus', 0.10, '2016-01-27', 'En halvfull dosa Göteborgs Rape (White Large)');
INSERT INTO Product VALUES (93, 2, 'SCigg', 0.10, '2016-01-27', 'En halvfull dosa Göteborgs Rape (White Large)');

-- Skapa Auction
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (100, '2017-02-20', '2017-03-20', '4000', '7000');
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (101, '2017-02-24', '2017-03-24', '75000', '89999');
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (102, '2017-01-11', '2017-02-11', '25000', '27000');
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (103, '2017-01-28', '2017-02-28', '6000', '15000');
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (104, '2017-02-17', '2017-03-17', '3000', '3500');
INSERT INTO Auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (93, '2016-02-17', '2016-03-17', '3000', '3500');

-- Skapa Customer
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Bruce', 'Wayne', '0707572626', 'imNotBatman@forReal.com', 'Wayne Mansion', 'Gotham', '66666');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Peter', 'Parker', '0721112223', 'Mj@ever.com', 'Queens Av7', 'New York', '95423');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Clark', 'Kent', '0707554845', 'Cryptonite@hate.com', 'Crystal Palace 1', 'Metropolis', '74554');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Charles', 'Xavier', '0744444445', 'ProffX@Xman.com', 'X-Mansion', 'Wachington', '15452');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Lex', 'Luthor', '0725416852', 'Ihatesuper@man.com', 'LexStreet', 'Metropolis', '22154');
INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address, City, Zip_Code)
VALUES ('Tor', 'Odensson', '0707522222', 'Mjolnir@hammer.com', 'VikingLane4', 'Valhalla', '78723');

-- Skapa Customer_Bid
INSERT INTO Customer_Bid (Customer_ID, Auction_ID, Bid, Bid_Date) VALUES (200, 302, '25500', '2017-02-04 19:05:37');
INSERT INTO Customer_Bid (Customer_ID, Auction_ID, Bid, Bid_Date) VALUES (200, 303, '6500', '2017-02-03 23:24:01');
INSERT INTO Customer_Bid (Customer_ID, Auction_ID, Bid, Bid_Date) VALUES (200, 302, '26000', '2017-02-09 07:45:05');
INSERT INTO Customer_Bid (Customer_ID, Auction_ID, Bid, Bid_Date) VALUES (200, 303, '8000', '2017-02-09 14:36:24');

-- Skapa Auction_History
INSERT INTO Auction_History (Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (98, 105, 200, 100, '2016-02-01 09:15:17', '2016-01-05', '2016-02-01', 25, 100);
INSERT INTO Auction_History (Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price)
VALUES (99, 106, 200, 250, '2016-02-05 13:00:00', '2016-01-22', '2016-02-22', 100, 250);
INSERT INTO auction_history VALUES (290, 95, 203, 2054, '2016-01-15 14:15:06', '2016-01-12', '2016-01-18', 1000, 2500);
INSERT INTO auction_history VALUES (291, 96, 204, 527, '2016-03-27 20:28:29', '2016-03-06', '2016-03-30', 500, 600);
INSERT INTO auction_history
VALUES (292, 97, 204, 10000, '2016-01-25 23:15:15', '2016-01-18', '2016-01-26', 5000, 15000);
INSERT INTO auction_history VALUES (293, 94, 203, 25, '2016-03-05 17:19:48', '2016-03-01', '2016-03-06', 20, 35);

-- Skapa Användare

INSERT INTO LogIn VALUES ('Gunnde63', 'root', TRUE);
INSERT INTO LogIn VALUES ('admin ', 'admin', TRUE );
INSERT INTO LogIN VALUES ('Simon', 'IloveGunnde', FALSE);
INSERT INTO LogIn VALUES ('employee', 'password', FALSE);

-- Registrera en produkt
DROP PROCEDURE IF EXISTS Register_one_product;
DELIMITER //
CREATE PROCEDURE Register_one_product(IN In_Supplier_ID INT, IN In_Product_Name VARCHAR(100), IN In_Commission DOUBLE,
                                         In_Entry_Date  DATETIME, In_Info TEXT)
  BEGIN
    INSERT INTO product (Supplier_ID, Product_Name, Commission, Entry_Date, Info)
    VALUES (In_Supplier_ID, In_Product_Name, In_Commission, In_Entry_Date, In_Info);
    SELECT *
    FROM Product
    ORDER BY Entry_Date DESC
    LIMIT 1;
  END//
DELIMITER ;

-- Skapa en auktion utifrån en viss produkt där man kan sätta utgångspris,
-- acceptpris samt start och slutdatum för auktionen.
DROP PROCEDURE IF EXISTS Create_auction;
DELIMITER //
CREATE PROCEDURE Create_auction(IN In_Product_ID   INT, IN In_Start_Date DATE, IN In_Start_Price DOUBLE,
                                   In_Accept_Price DOUBLE)
  BEGIN
    INSERT INTO auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price)
    VALUES (In_Supplier_ID, In_Product_Name, In_Commission, In_Entry_Date, In_Info);
    SELECT *
    FROM auction
    ORDER BY Start_Date ASC
    LIMIT 1;

  END//
DELIMITER ;

-- Lista pågående auktioner samt kunna se det högsta budet och vilken kund
-- som lagt det.
CREATE VIEW List_ongoing_auctions AS

  SELECT
    Product.Product_Name,
    MAX(Customer_Bid.Bid) AS Bid,
    Customer.First_Name,
    Customer.Last_Name
  FROM Customer
    INNER JOIN Customer_Bid ON Customer.Customer_ID = Customer_Bid.Customer_ID
    INNER JOIN Auction ON Customer_Bid.Auction_ID = Auction.Auction_ID
    INNER JOIN Product ON Auction.Product_ID = Product.Product_ID
  GROUP BY Product.Product_Name
  ORDER BY Customer_Bid.Bid DESC;

-- Se budhistoriken på en viss auktion, samt vilka kunder som lagt buden
SELECT
  Auction.Auction_ID,
  Product.Product_Name,
  CONCAT(Customer.First_Name, ' ', Customer.Last_Name) AS Customer_Name,
  Customer_Bid.Bid,
  Customer_Bid.Bid_Date
FROM Customer
  INNER JOIN Customer_Bid ON Customer.Customer_ID = Customer_Bid.Customer_ID
  INNER JOIN Auction ON Customer_Bid.Auction_ID = Auction.Auction_ID
  INNER JOIN Product ON Auction.Product_ID = Product.Product_ID
GROUP BY Auction.Auction_ID, Product.Product_Name, Customer_Name, Customer_Bid.Bid, Customer_Bid.Bid_Date
ORDER BY Customer_Bid.Bid_Date DESC;

-- Vilka auktioner avslutas under ett visst datumintervall? Samt vad blir
-- provisionen för varje auktion inom det intervallet?
DROP PROCEDURE IF EXISTS Est_auction_report;
DELIMITER //
CREATE PROCEDURE Est_auction_report(IN mStart_Date DATE, IN mEnd_Date DATE)
  BEGIN

    (SELECT
       Auction_History.Start_Date,
       Auction_History.End_Date,
       Auction_History.Date_Sold,
       Auction_History.Final_Bid,
       SUM(Auction_History.Final_Bid * Product.Commission) AS Commission,
       Product.Product_Name
     FROM Auction_History
       INNER JOIN Product ON Auction_History.Product_ID = Product.Product_ID
     WHERE Auction_History.Start_Date AND Auction_History.End_Date BETWEEN mStart_Date AND mEnd_Date
     GROUP BY Start_Date, End_Date, Date_Sold, Final_Bid, Product.Product_Name
     ORDER BY Start_Date DESC)
    UNION
    (SELECT
       Auction.Start_Date,
       Auction.End_Date,
       'Unsold',
       'Unsold',
       MAX(Customer_Bid.Bid * Product.Commission) AS test,
       Product.Product_Name
     FROM Auction
       INNER JOIN Customer_Bid ON Auction.Auction_ID = Customer_Bid.Auction_ID
       INNER JOIN Product ON Auction.Product_ID = Product.Product_ID
     WHERE Auction.Start_Date AND Auction.End_Date BETWEEN mStart_Date AND mEnd_Date
     GROUP BY Start_Date, End_Date, Product.Product_Name
     ORDER BY Start_Date DESC);

  END //
DELIMITER ;

-- När en auktion är avslutad och det finns en köpare så skall auktionen flyttas till en
-- auktionshistoriktabell
SET GLOBAL EVENT_SCHEDULER = ON;
DROP EVENT IF EXISTS End_of_auction;
CREATE EVENT End_of_auction
  ON SCHEDULE EVERY '1' DAY
  STARTS CURRENT_DATE
DO
  BEGIN

    INSERT INTO auction_history (Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price)
      SELECT
        auction.Auction_ID,
        auction.Product_ID,
        customer_bid.Customer_ID,
        MAX(customer_bid.Bid) AS MaxBid,
        customer_bid.Bid_Date,
        auction.Start_Date,
        auction.End_Date,
        auction.Start_Price,
        Accept_Price
      FROM auction
        LEFT JOIN customer_bid ON customer_bid.Auction_ID = auction.Auction_ID
      WHERE auction.End_Date < current_date()
      GROUP BY auction.Auction_ID;

    DELETE FROM auction
    WHERE auction.Auction_ID IN (SELECT auction_history.Auction_ID
                                 FROM auction_history);

  END;

-- Visa en kundlista på alla kunder som köpt något, samt vad deras totala
-- ordervärde är.
DROP VIEW IF EXISTS Customer_history;

CREATE VIEW Customer_history
AS
  SELECT
    customer.Customer_ID,
    First_Name,
    Last_Name,
    sum(Final_Bid) AS TotalPurchases
  FROM customer
    INNER JOIN auction_history ON customer.Customer_ID = auction_history.Customer_ID
  GROUP BY auction_history.Customer_ID;

-- Trigger som lägger över sålda auctions med acceptpris till auction_history
DROP TRIGGER IF EXISTS Sold_with_accept_price;

DELIMITER //

CREATE TRIGGER Sold_with_accept_price
AFTER INSERT ON customer_bid
FOR EACH ROW
  BEGIN
    IF new.Bid >= (SELECT max(auction.Accept_Price)
                   FROM auction
                     INNER JOIN customer_bid ON auction.Auction_ID = customer_bid.Auction_ID)
    THEN
      INSERT INTO auction_history (Auction_ID, Product_ID, Customer_ID, Final_Bid, Date_Sold, Start_Date, End_Date, Start_Price, Accept_Price)
        SELECT
          auction.Auction_ID,
          auction.Product_ID,
          customer_bid.Customer_ID,
          new.Bid AS MaxBid,
          customer_bid.Bid_Date,
          auction.Start_Date,
          auction.End_Date,
          auction.Start_Price,
          Accept_Price
        FROM auction
          INNER JOIN customer_bid ON new.Auction_ID = auction.Auction_ID
        WHERE new.Bid >= auction.Accept_Price
        GROUP BY auction.Auction_ID;



    END IF;

    DELETE FROM auction WHERE auction.Auction_ID IN (SELECT auction_history.Auction_ID FROM auction_history);

  END //

DELIMITER ;


-- Vad den totala provisionen är per månad.
DROP VIEW IF EXISTS Commission_per_month_view;

CREATE VIEW Commission_per_month_view
AS

  SELECT
    YEAR(Auction_History.Date_Sold)                     AS Year,
    MONTHNAME(Auction_History.Date_Sold)                AS Month,
    SUM(Product.Commission * Auction_History.Final_Bid) AS Commission
  FROM Product
    INNER JOIN Auction_History ON product.Product_ID = auction_history.Product_ID
  GROUP BY Year, Month
  ORDER BY Year, Month;

-- View för tab ongoing auctions
CREATE VIEW Ongoing_auctions AS

  SELECT
    Product.Product_Name,
    Customer_Bid.Bid,
    Customer_Bid.Bid_Date,
    Auction.Auction_ID,
    Customer.First_Name,
    Customer.Last_Name
  FROM Customer
    INNER JOIN Customer_Bid ON Customer.Customer_ID = Customer_Bid.Customer_ID
    INNER JOIN Auction ON Customer_Bid.Auction_ID = Auction.Auction_ID
    INNER JOIN Product ON Auction.Product_ID = Product.Product_ID
  ORDER BY Customer_Bid.Bid DESC;

-- For not admin window
DROP VIEW IF EXISTS bidding_view;
CREATE VIEW bidding_view
AS
  SELECT
    Product.Product_Name,
    Supplier.Company_Name,
    MAX(Customer_Bid.Bid) AS MaxBid,
    Auction.Accept_Price,
    Auction.Auction_ID
  FROM
    Product
    INNER JOIN Supplier ON Product.Supplier_ID = Supplier.Supplier_ID
    INNER JOIN Auction ON Product.Product_ID = Auction.Product_ID
    INNER JOIN Customer_Bid ON Auction.Auction_ID = Customer_Bid.Auction_ID
  GROUP BY Product.Product_Name, Supplier.Company_Name, Auction.Accept_Price, Auction.Auction_ID
  ORDER BY Product_Name;

