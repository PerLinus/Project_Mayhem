package models;

/**
 * Created by PereZ on 2017-02-14.
 */
public class CommissionForecast {

    private String productName;
    private String endDate;
    private double commission;

    public CommissionForecast(String productName, String endDate, double commission) {
        this.productName = productName;
        this.endDate = endDate;
        this.commission = commission;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
