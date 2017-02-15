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
import models.CommissionForecast;
import models.CommissionPerMonth;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

/**
 * Created by MacsMac on 2017-02-14.
 */
public class UserViewController {

    @FXML
    private TableView twAuctionList;
    @FXML
    private TableColumn tcProductName, tcSupplier, tcHighestBid, tcAcceptPrice;
    @FXML
    private TextField txfSearchWord;

    private List<Auction> auctionsList = new ArrayList<>();

    public void initialize(){
        loadAllAuctions();
    }

    private void loadAllAuctions() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Ongoing_Auctions")) {

                    while (resultSet.next()) {
                        String auctionID = resultSet.getString("Product_Name");
                        double bid = resultSet.getDouble("Bid");
                        double commission = resultSet.getDouble("Commission");


                        auctionsList.add(new Auction());

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
