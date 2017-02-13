package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController {


    public void openAddSupplier(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/addSupplierSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Supplier");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void openAddCustomer(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/addCustomerSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openAddProduct(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/addProductSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openAddAuction(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/addAuctionSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Auction");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openOngoingAuctions(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/ongoingAuctionsSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ongoing Auctions");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}