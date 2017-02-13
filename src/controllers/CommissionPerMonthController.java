package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CommissionPerMonth;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommissionPerMonthController {

    @FXML
    private TableColumn tcYear, tcMonth, tcCommission;
    @FXML
    private TableView twCommissionPerMonth;

    private List<String> yearList = new ArrayList<>();
    private List<String> monthList = new ArrayList<>();
    private List<Double> commissionList = new ArrayList<>();
    private List<CommissionPerMonth> commissionPerMonthList = new ArrayList<>();

    public void initialize() {
        loadAuctionList();
        ObservableList<String> observableYearList = FXCollections.observableArrayList(yearList);
        ObservableList<String> observableMonthList = FXCollections.observableArrayList(monthList);
        ObservableList<Double> observableCommissionList = FXCollections.observableArrayList(commissionList);

        for (int i = 0 ; i < yearList.size() ; i++){
            tcYear.setText(yearList.get(i));
        }
        for (int i = 0 ; i < monthList.size() ; i++){
            tcMonth.setText(monthList.get(i));
        }
        for (int i = 0 ; i < commissionList.size() ; i++){
            tcCommission.setText(commissionList.get(i).toString());
        }
/**
            tcYear.setCellValueFactory(new PropertyValueFactory<CommissionPerMonth, String>("year"));
            tcMonth.setCellFactory(new PropertyValueFactory<CommissionPerMonth, String>("month"));
            tcCommission.setCellValueFactory(new PropertyValueFactory<CommissionPerMonth, Double>("Commission"));

            twCommissionPerMonth.getItems().setAll(commissionPerMonthList);
 */

    }


    private void loadAuctionList() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Commission_Per_Month_View")) {

                    while (resultSet.next()) {
                        String year = resultSet.getString("Year");
                        String month = resultSet.getString("Month");
                        double commission = resultSet.getDouble("Commission");

                        yearList.add(year);
                        monthList.add(month);
                        commissionList.add(commission);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
