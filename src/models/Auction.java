package models;


public class Auction {

    private int auctionID;
    private int productID;
    private String startDate;
    private String endDate;
    private double startPrice;
    private double acceptPrice;

    public Auction(int auctionID, int productID, String startDate, String endDate, double startPrice, double acceptPrice) {
        this.auctionID = auctionID;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.acceptPrice = acceptPrice;
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

    public double getAcceptPrice() {
        return acceptPrice;
    }

    public void setAcceptPrice(double acceptPrice) {
        this.acceptPrice = acceptPrice;
    }

    @Override
    public String toString() {
        return "AuctionID" + auctionID;
    }
}
