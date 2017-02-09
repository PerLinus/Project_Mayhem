package models;


public class Product {

    private int productID;
    private int supplierID;
    private String productName;
    private Double commission;
    private String entryDate;
    private String text;

    public Product(int productID, int supplierID, String productName, Double commission, String entryDate, String text) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.productName = productName;
        this.commission = commission;
        this.entryDate = entryDate;
        this.text = text;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return productName;
    }
}
