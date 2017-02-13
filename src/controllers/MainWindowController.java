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
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
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
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
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
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
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
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
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
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openCommissonForecast(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/commissionForecastSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Commission Forecast");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCustomerPurchaseList(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/customerListSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Customer Purchase List");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCommissionPerMonthList(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/commissionForecastSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Earned Commission");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}