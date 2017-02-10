
Use project_mayhem;

-- Registrera en produkt

DROP PROCEDURE IF EXISTS Register_one_product;

DELIMITER //

CREATE PROCEDURE Register_one_product(IN In_Supplier_ID INT, IN In_Product_Name VARCHAR(100), IN In_Commission DOUBLE,In_Entry_Date DATETIME, In_Info TEXT)
  BEGIN
    INSERT INTO product (Supplier_ID, Product_Name, Commission, Entry_Date, Info) values (In_Supplier_ID ,In_Product_Name,In_Commission,In_Entry_Date,In_Info);
    SELECT * FROM Product
    ORDER BY Entry_Date DESC LIMIT 1;
  END//
DELIMITER ;

CALL Register_one_product(1, 'Portabel högtalare av BOSE', 10, '2017-01-10 09:13:37', 'Om du vill att hela stranden ska lyssna på din musik!');

-- Skapa en auktion utifrån en viss produkt där man kan sätta utgångspris,
-- acceptpris samt start och slutdatum för auktionen.

DROP PROCEDURE IF EXISTS Create_Auction;

DELIMITER //

CREATE PROCEDURE Create_Auction(IN In_Product_ID INT, IN In_Start_Date DATE, IN In_Start_Price DOUBLE,In_Accept_Price DOUBLE)
  BEGIN
    INSERT INTO auction (Product_ID, Start_Date, End_Date, Start_Price, Accept_Price) values (In_Supplier_ID ,In_Product_Name,In_Commission,In_Entry_Date,In_Info);
    SELECT * FROM auction
    ORDER BY Start_Date ASC LIMIT 1;
  END//
DELIMITER ;

-- Test Create_Auction

CALL Create_Auction (105,'2017-02-17', 3000, 4500);

-- test - end

-- Lista pågående auktioner samt kunna se det högsta budet och vilken kund
-- som lagt det.

CREATE VIEW current_auctions AS

  SELECT Product.Product_Name, MAX(Customer_Bid.Bid) AS Bid , Customer.First_Name, Customer.Last_Name
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

-- Vad den totala provisionen är per månad.

SELECT MONTHNAME(Auction_History.Date_Sold) AS Month, SUM(Product.Commission * Auction_History.Final_Bid) AS Commission FROM Product
  INNER JOIN Auction_History ON product.Product_ID = auction_history.Product_ID
GROUP BY Month
ORDER BY Commission;

--  Visa en kundlista på alla kunder som köpt något, samt vad deras totala
-- ordervärde är.

DROP VIEW IF EXISTS CustomerHistory;

CREATE VIEW CustomerHistory
AS
  SELECT customer.Customer_ID, First_Name, Last_Name, sum(Final_Bid) AS TotalSales FROM customer
    INNER JOIN auction_history ON customer.Customer_ID = auction_history.Customer_ID
  GROUP BY auction_history.Customer_ID;

-- När en auktion är avslutad och det finns en köpare så skall auktionen flyttas till en
-- auktionshistoriktabell

SET GLOBAL EVENT_SCHEDULER = ON;


CREATE EVENT end_of_auction
  ON SCHEDULE EVERY '1' DAY
  STARTS CURRENT_DATE
DO
  BEGIN


    INSERT INTO auction_history (Auction_ID,Product_ID,Customer_ID,Final_Bid,Date_Sold,Start_Date,End_Date,Start_Price,Accept_Price)
      SELECT auction.Auction_ID,auction.Product_ID,customer_bid.Customer_ID,MAX(customer_bid.Bid) AS MaxBid,
        customer_bid.Bid_Date, auction.Start_Date, auction.End_Date, auction.Start_Price, Accept_Price FROM auction
        LEFT JOIN customer_bid ON customer_bid.Auction_ID = auction.Auction_ID
      WHERE auction.End_Date < now()
      GROUP BY auction.Auction_ID;

    DELETE FROM auction WHERE auction.Auction_ID IN (SELECT auction_history.Auction_ID FROM auction_history);

  END;