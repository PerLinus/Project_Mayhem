package models;

/**
 * Created by Linus Nilsson on 2017-02-14.
 */
public class CurrentAuctions {

    private int auctionID;
    private String productName;

    public CurrentAuctions(int auctionID, String productName) {
        this.auctionID = auctionID;
        this.productName = productName;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return auctionID + " " + productName;
    }
}
