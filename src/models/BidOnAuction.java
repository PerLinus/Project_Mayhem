package models;


public class BidOnAuction {

    private String productName;
    private String supplierName;
    private int auctionID;
    private double highestBid;
    private double acceptPrice;
    private double startPrice;

    public BidOnAuction(String productName, String supplierName, int auctionID, double highestBid, double acceptPrice, double startPrice) {
        this.productName = productName;
        this.supplierName = supplierName;
        this.auctionID = auctionID;
        this.highestBid = highestBid;
        this.acceptPrice = acceptPrice;
        this.startPrice = startPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public double getAcceptPrice() {
        return acceptPrice;
    }

    public void setAcceptPrice(double acceptPrice) {
        this.acceptPrice = acceptPrice;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }
}
