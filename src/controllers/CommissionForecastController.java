package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CommissionForecast;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommissionForecastController {

    List<CommissionForecast> commissionForecastList = new ArrayList<>();

    @FXML
    private DatePicker dpFromDate, dpToDate;

    @FXML
    private TableView twCommissionForecast;

    @FXML
    private TableColumn tcProductName, tcEndDate, tcCommission;


    public void onClickGetForecast(ActionEvent actionEvent) {

        LocalDate startDate = dpFromDate.getValue();
        LocalDate endDate = dpToDate.getValue();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (CallableStatement cstm = connection.prepareCall("CALL Est_auction_report(?,?)")) {

                cstm.setDate(1, Date.valueOf(startDate));
                cstm.setDate(2, Date.valueOf(endDate));

                ResultSet result = cstm.executeQuery();

                while (result.next()){

                    String productName = result.getString("Product_Name");
                    String end_Date = result.getString("End_Date");
                    double commission = result.getDouble("Commission");

                    commissionForecastList.add(new CommissionForecast(productName, end_Date, commission));

                    ObservableList<CommissionForecast> list = FXCollections.observableArrayList(commissionForecastList);
                    tcProductName.setCellValueFactory(new PropertyValueFactory<CommissionForecast, String >("productName"));
                    tcEndDate.setCellValueFactory(new PropertyValueFactory<CommissionForecast, String >("endDate"));
                    tcCommission.setCellValueFactory(new PropertyValueFactory<CommissionForecast, Double >("commission"));
                    twCommissionForecast.setItems(list);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
