package models;

/**
 * Created by MacsMac on 2017-02-13.
 */
public class CommissionPerMonth {

    private String year;
    private String month;
    private double commission;


    public CommissionPerMonth(String year, String month, double commission) {
        this.year = year;
        this.month = month;
        this.commission = commission;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCommission() {
        return Math.round(commission) + " kr";
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
