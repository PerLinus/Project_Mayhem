package models;


public class Auction_History {

    private int auctionID;
    private int productID;
    private int customerID;
    private double finalBid;
    private String dateSold;
    private String startDate;
    private String endDate;
    private double startPrice;
    private double acceptPris;

    public Auction_History(int auctionID, int productID, int customerID, double finalBid, String dateSold, String startDate, String endDate, double startPrice, double acceptPris) {
        this.auctionID = auctionID;
        this.productID = productID;
        this.customerID = customerID;
        this.finalBid = finalBid;
        this.dateSold = dateSold;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.acceptPris = acceptPris;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getFinalBid() {
        return finalBid;
    }

    public void setFinalBid(double finalBid) {
        this.finalBid = finalBid;
    }

    public String getDateSold() {
        return dateSold;
    }

    public void setDateSold(String dateSold) {
        this.dateSold = dateSold;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getAcceptPris() {
        return acceptPris;
    }

    public void setAcceptPris(double acceptPris) {
        this.acceptPris = acceptPris;
    }

    @Override
    public String toString() {
        return "AuctionID" + auctionID;
    }
}

