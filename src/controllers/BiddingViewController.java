package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Auction;
import models.BidOnAuction;
import models.CommissionForecast;
import models.CommissionPerMonth;

import java.awt.*;
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
    private TableColumn tcProductName, tcSupplier, tcHighestBid, tcAcceptPrice;
    @FXML
    private TextField txfSearchWord;

    private List<BidOnAuction> auctionsList = new ArrayList<>();

    public void initialize(){
        loadAllAuctions();
    }

    private void loadAllAuctions() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM bidding_view")) {

                    while (resultSet.next()) {
                        String productName = resultSet.getString("Product_Name");
                        String supplierName = resultSet.getString("Company_Name");
                        double highestBid = resultSet.getDouble("MaxBid");
                        double acceptPrice = resultSet.getDouble("Accept_Price");

                        auctionsList.add(new BidOnAuction(productName, supplierName, highestBid, acceptPrice));

                        ObservableList<BidOnAuction> list = FXCollections.observableArrayList(auctionsList);
                        tcProductName.setCellValueFactory(new PropertyValueFactory<BidOnAuction, String >("productName"));
                        tcSupplier.setCellValueFactory(new PropertyValueFactory<BidOnAuction, String >("supplierName"));
                        tcHighestBid.setCellValueFactory(new PropertyValueFactory<BidOnAuction, Double >("highestBid"));
                        tcAcceptPrice.setCellValueFactory(new PropertyValueFactory<BidOnAuction, Double >("acceptPrice"));
                        twAuctionList.setItems(list);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClickOpenBiddingWindow(ActionEvent actionEvent) {

    }

    public void onClicksearch(ActionEvent actionEvent) {

    }
}
