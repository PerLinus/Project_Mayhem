package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import models.OngoingAuctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OngoingAuctionsController {

    @FXML
    private ListView lwAuctions;

    @FXML
    private TableView twBidHistory;

    @FXML
    private TableColumn tcCustomer, tcBid, tcBidDate;

    private List<OngoingAuctions> bidList = new ArrayList<>();

//            //the initial List
//            ArrayList list = new ArrayList();
//list.add("ID_1");
//list.add("ID_2");
//list.add("ID_3);
//        list.add("ID_2); //duplicate entry - needs to be removed
//    //convert the List into a Set
//    Set set = new HashSet(list);
//    //create a new List from the Set
//    ArrayList uniqueList = new ArrayList(set);

    public void initialize(){

        loadOngoingAuctions();
        Set set = new HashSet(bidList);
        ObservableList<OngoingAuctions> observableOngoingAuctionsList = FXCollections.observableArrayList(set);
        lwAuctions.setItems(observableOngoingAuctionsList);

    }

    private void loadOngoingAuctions() {

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

                        bidList.add(new OngoingAuctions(auctionID, bid, firstName, lastName, bidDate, productName));


                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


    public void onMouseClicked(MouseEvent mouseEvent) {
    }
}
