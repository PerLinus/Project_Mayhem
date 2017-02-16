package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * Created by MacsMac on 2017-02-14.
 */
public class BiddingViewController {

    @FXML
    private TableView twAuctionList;
    @FXML
    private TableColumn tcProductName, tcSupplier, tcStartPrice, tcHighestBid, tcAcceptPrice;
    @FXML
    private TextField txfSearchWord, txfBid;
    @FXML
    private ComboBox cbCustomer;

    private List<BidOnAuction> auctionsList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();

    public void initialize(){
        loadAllAuctions();
        loadAllCustomers();

    }

    private void loadAllAuctions() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM bidding_view")) {

                    while (resultSet.next()) {
                        String productName = resultSet.getString("Product_Name");
                        String supplierName = resultSet.getString("Company_Name");
                        double startPrice = resultSet.getDouble("Start_Price");
                        double highestBid = resultSet.getDouble("MaxBid");
                        double acceptPrice = resultSet.getDouble("Accept_Price");
                        int auctionID = resultSet.getInt("Auction_ID");


                        auctionsList.add(new BidOnAuction(productName, supplierName, auctionID, highestBid, acceptPrice, startPrice));

                        ObservableList<BidOnAuction> list = FXCollections.observableArrayList(auctionsList);
                        tcProductName.setCellValueFactory(new PropertyValueFactory<BidOnAuction, String >("productName"));
                        tcSupplier.setCellValueFactory(new PropertyValueFactory<BidOnAuction, String >("supplierName"));
                        tcHighestBid.setCellValueFactory(new PropertyValueFactory<BidOnAuction, Double >("highestBid"));
                        tcAcceptPrice.setCellValueFactory(new PropertyValueFactory<BidOnAuction, Double >("acceptPrice"));
                        tcStartPrice.setCellValueFactory(new PropertyValueFactory<BidOnAuction, Double >("startPrice"));
                        twAuctionList.setItems(list);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadAllCustomers(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT Customer.First_Name, Customer.Last_Name, Customer.Customer_ID FROM Customer;")) {

                    while(resultSet.next()){
                        String firstName = resultSet.getString("First_Name");
                        String lastName = resultSet.getString("Last_Name");
                        int customerID = resultSet.getInt("Customer_ID");

                        customerList.add(new Customer(customerID, firstName, lastName));

                        ObservableList<Customer> observableCustomerList = FXCollections.observableList(customerList);
                        cbCustomer.setItems(observableCustomerList);
                        cbCustomer.getSelectionModel().selectFirst();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClicksearch(ActionEvent actionEvent) {

    }

    public void placeBid(ActionEvent actionEvent) {

        Customer choosenCustomer = (Customer) cbCustomer.getSelectionModel().getSelectedItem();
        BidOnAuction choosenAuction = (BidOnAuction) twAuctionList.getSelectionModel().getSelectedItem();
        int customerID = choosenCustomer.getCustomerID();
        int auctionID = choosenAuction.getAuctionID();
        double bid = Double.parseDouble(txfBid.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(" INSERT INTO Customer_Bid (Customer_ID, Auction_ID, Bid) VALUES (?,?,?);")) {
                preparedStatement.setInt(1, customerID);
                preparedStatement.setInt(2, auctionID);
                preparedStatement.setDouble(3, bid);
                preparedStatement.execute();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        auctionsList.clear();
        loadAllAuctions();
        txfBid.clear();
    }
}
