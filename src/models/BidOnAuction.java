package models;


public class BidOnAuction {

    private String productName;
    private String supplierName;
    private double highestBid;
    private double acceptPrice;

    public BidOnAuction(String productName, String supplierName, double highestBid, double acceptPrice) {
        this.productName = productName;
        this.supplierName = supplierName;
        this.highestBid = highestBid;
        this.acceptPrice = acceptPrice;
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
}
