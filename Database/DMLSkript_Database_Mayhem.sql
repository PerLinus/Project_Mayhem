
Use project_mayhem;

-- Test Register_one_product
CALL Register_one_product(1, 'Portabel högtalare av BOSE', 10, '2017-01-10 09:13:37', 'Om du vill att hela stranden ska lyssna på din musik!');

-- Test Create_Auction
CALL Create_Auction (105,'2017-02-17', 3000, 4500);

-- Test list_ongoing_auctions
SELECT * FROM list_ongoing_auctions;



-- Vad den totala provisionen är per månad.
SELECT YEAR(Auction_History.Date_Sold) AS Year, MONTHNAME(Auction_History.Date_Sold) AS Month, SUM(Product.Commission * Auction_History.Final_Bid) AS Commission FROM Product
  INNER JOIN Auction_History ON product.Product_ID = auction_history.Product_ID
GROUP BY Month
ORDER BY Year, Month;

-- Test Est_Auction_Report
CALL Est_Auction_Report('2016-01-01', '2017-05-05');