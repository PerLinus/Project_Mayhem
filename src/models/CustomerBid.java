package models;


public class CustomerBid {

    private int bidID;
    private int customerID;
    private int auctionID;
    private Double bid;
    private String bidDate;

    public CustomerBid(int bidID, int customerID, int auctionID, Double bid, String bidDate) {
        this.bidID = bidID;
        this.customerID = customerID;
        this.auctionID = auctionID;
        this.bid = bid;
        this.bidDate = bidDate;
    }

    public int getBidID() {
        return bidID;
    }

    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    @Override
    public String toString() {
        return "BidID" + bidID;
    }
}


