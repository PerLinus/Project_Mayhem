package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Linus Nilsson on 2017-02-14.
 */
public class BidsOnCurrentAuctions {

    private int auctionID;
    private double bid;
    private String firstName;
    private String lastName;
    private String bidDate;
    private String productName;

    public BidsOnCurrentAuctions(int auctionID, double bid, String firstName, String lastName, String bidDate, String productName) {
        this.auctionID = auctionID;
        this.bid = bid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bidDate = bidDate;
        this.productName = productName;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public String getBid() {
        return Math.round(bid) + " kr";
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return auctionID + " " + productName;
    }
}
