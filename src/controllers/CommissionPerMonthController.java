package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class CommissionPerMonthController {

    @FXML
    private TableView twYear, twMonth, twCommission;

    public void initialize(){
        loadAuctionList();
    }
}
