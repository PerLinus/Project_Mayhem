package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddAuctionController {

    @FXML
    private DatePicker dpStartDate, dpEndDate;
    @FXML
    private TextField txfStartPrice, txfAcceptPrice;
    @FXML
    private ComboBox cbChooseProduct;

    private List<Product> productList = new ArrayList<>();

    public void initialize(){
        loadProductList();
        ObservableList<Product> observableList = FXCollections.observableArrayList(productList);
        cbChooseProduct.setItems(observableList);
        cbChooseProduct.getSelectionModel().selectFirst();

    }

    private void loadProductList() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem", "root", "root")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery("SELECT * FROM Product WHERE Product_ID NOT IN (SELECT Product_ID FROM Auction)")) {
                    while (result.next()) {
                        int productID = result.getInt("Product_ID");
                        int supplierID = result.getInt("Supplier_ID");
                        String productName = result.getString("Product_Name");
                        double commission = result.getDouble("Commission");
                        String entryDate = result.getString("Entry_Date");
                        String description = result.getString("Info");

                        productList.add(new Product(productID, supplierID, productName, commission, entryDate, description));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void onClickAddAuction(ActionEvent actionEvent) {

        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();

        String startPrice = txfStartPrice.getText();
        String acceptPrice = txfAcceptPrice.getText();
        Product selectedProduct = (Product) cbChooseProduct.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(validateUserInput()) {

            try {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem", "root", "root")) {
                    try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Auction(Product_ID, Start_Date, End_Date, Start_Price, Accept_Price) VALUES (?,?,?,?,?)")) {
                        pstm.setInt(1, selectedProduct.getProductID());
                        pstm.setDate(2, Date.valueOf(startDate));
                        pstm.setDate(3, Date.valueOf(endDate));
                        pstm.setString(4, startPrice);
                        pstm.setString(5, acceptPrice);

                        pstm.execute();
                        alert.setTitle("New auction added");
                        alert.setContentText("The auction is sucessfully added to database.");
                        alert.showAndWait();
                        alert.close();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Database error!");
                error.setContentText("Auction could not be added. Contact support!");
                error.showAndWait();
                error.close();
            }
            productList.clear();
            loadProductList();
            ObservableList<Product> observableList = FXCollections.observableArrayList(productList);
            cbChooseProduct.setItems(observableList);
            cbChooseProduct.getSelectionModel().selectFirst();

            dpStartDate.setValue(null);
            dpEndDate.setValue(null);
            txfStartPrice.clear();
            txfAcceptPrice.clear();
        } else {
            Alert error = new Alert((Alert.AlertType.ERROR));
            error.setTitle("Invalid input");
            error.setContentText("All fields must be filled!");
            error.showAndWait();
            error.close();
        }
    }

    public boolean validateUserInput(){
        if(txfStartPrice.getText().trim().length() == 0){
            return false;
        } else if (txfAcceptPrice.getText().trim().length() == 0){
            return false;
        } else if(dpStartDate.getValue() == null) {
            return false;
        } else if(dpEndDate.getValue() == null) {
            return false;
        } else{
            return true;
        }
    }
}
