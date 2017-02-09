package models;


public class Auction {

    private int auctionID;
    private int productID;
    private String startDate;
    private String endDate;
    private Double startPrice;
    private Double acceptPrice;

    public Auction(int auctionID, int productID, String startDate, String endDate, Double startPrice, Double acceptPrice) {
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

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getAcceptPrice() {
        return acceptPrice;
    }

    public void setAcceptPrice(Double acceptPrice) {
        this.acceptPrice = acceptPrice;
    }

    @Override
    public String toString() {
        return "AuctionID" + auctionID;
    }
}
