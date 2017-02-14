package models;

/**
 * Created by PereZ on 2017-02-14.
 */
public class Customer_history {

    private int customerID;
    private String firstName;
    private String lastName;
    private double totalPurchase;

    public Customer_history(int customerID, String firstName, String lastName, double totalPurchase) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPurchase = totalPurchase;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

}
