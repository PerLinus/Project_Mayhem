package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    public void onClickOpenBiddingWindow(ActionEvent actionEvent) {

    }

    public void onClicksearch(ActionEvent actionEvent) {

    }
}
