
Use project_mayhem;

-- Test Register_one_product
CALL Register_one_product(1, 'Portabel högtalare av BOSE', 10, '2017-01-10 09:13:37', 'Om du vill att hela stranden ska lyssna på din musik!');

-- Test Create_Auction
CALL Create_Auction (105,'2017-02-17', 3000, 4500);

-- Test list_ongoing_auctions
SELECT * FROM list_ongoing_auctions;

-- Test Est_Auction_Report
CALL Est_Auction_Report('2016-01-01', '2017-05-05');

SELECT * FROM supplier;

SELECT * FROM customer_history;

