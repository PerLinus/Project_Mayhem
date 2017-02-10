Use project_mayhem;

-- Testcalls
CALL Register_one_product(1, 'Portabel högtalare av BOSE', 10, '2017-01-10 09:13:37', 'Om du vill att hela stranden ska lyssna på din musik!');
CALL Create_Auction (105,'2017-02-17', 3000, 4500);

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
-- ordervärde är (som vy)

DROP VIEW IF EXISTS CustomerHistory;

CREATE VIEW CustomerHistory
AS
  SELECT customer.Customer_ID, First_Name, Last_Name, sum(Final_Bid) AS TotalSales FROM customer
    INNER JOIN auction_history ON customer.Customer_ID = auction_history.Customer_ID
  GROUP BY auction_history.Customer_ID;

SELECT * FROM CustomerHistory;