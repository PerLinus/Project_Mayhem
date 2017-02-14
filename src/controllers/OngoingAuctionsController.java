package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.BidsOnCurrentAuctions;
import models.CurrentAuctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OngoingAuctionsController {

    @FXML
    private ListView lwAuctions;

    @FXML
    private TableView twBidHistory;

    @FXML
    private TableColumn tcCustomer, tcBid, tcBidDate;

    private List<CurrentAuctions> auctionList = new ArrayList<>();
    private List<BidsOnCurrentAuctions> bidList = new ArrayList<>();


    public void initialize(){

        loadBidsOnCurrentAuctions();
        loadCurrentAuctions();

        tcCustomer.setCellValueFactory(new PropertyValueFactory<BidsOnCurrentAuctions, String>("Name"));
        tcBid.setCellValueFactory(new PropertyValueFactory<BidsOnCurrentAuctions, String>("Bid"));
        tcBidDate.setCellValueFactory(new PropertyValueFactory<BidsOnCurrentAuctions, String>("BidDate"));

        ObservableList<CurrentAuctions> observableAuctionList = FXCollections.observableArrayList(auctionList);
        lwAuctions.setItems(observableAuctionList);
        lwAuctions.getSelectionModel().selectFirst();
        onMouseClicked(null);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {

        List<BidsOnCurrentAuctions> tempList = new ArrayList<>();
        CurrentAuctions currentAuctions = (CurrentAuctions)lwAuctions.getSelectionModel().getSelectedItem();

        for(int i = 0; i < bidList.size(); i++){
            if(currentAuctions.getAuctionID() == bidList.get(i).getAuctionID()){
                tempList.add(bidList.get(i));
            }
        }

        ObservableList<BidsOnCurrentAuctions> list = FXCollections.observableArrayList(tempList);
        twBidHistory.setItems(list);

    }

    private void loadCurrentAuctions() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT Auction.Auction_ID, Product.Product_Name FROM Auction INNER JOIN Product ON Auction.Product_ID = Product.Product_ID ")) {

                    while (resultSet.next()) {

                        int auctionID = resultSet.getInt("Auction_ID");
                        String productName = resultSet.getString("Product_Name");
                        auctionList.add(new CurrentAuctions(auctionID, productName));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void loadBidsOnCurrentAuctions() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Ongoing_auctions")) {

                    while (resultSet.next()) {


                        int auctionID = resultSet.getInt("Auction_ID");
                        double bid = resultSet.getDouble("Bid");
                        String firstName = resultSet.getString("First_name");
                        String lastName = resultSet.getString("Last_name");
                        String bidDate = resultSet.getString("Bid_date");
                        String productName = resultSet.getString("Product_Name");
                        bidList.add(new BidsOnCurrentAuctions(auctionID, bid, firstName, lastName, bidDate, productName));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
