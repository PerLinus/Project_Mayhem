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

    private List<CommissionPerMonth> commissionPerMonthList = new ArrayList<>();

    public void initialize() {
        loadAuctionList();
        ObservableList<CommissionPerMonth> list = FXCollections.observableArrayList(commissionPerMonthList);
        tcYear.setCellValueFactory(new PropertyValueFactory<CommissionPerMonth, String>("Year"));
        tcMonth.setCellValueFactory(new PropertyValueFactory<CommissionPerMonth, String>("Month"));
        tcCommission.setCellValueFactory(new PropertyValueFactory<CommissionPerMonth, Double>("Commission"));
        twCommissionPerMonth.setItems(list);

    }


    private void loadAuctionList() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Commission_Per_Month_View")) {

                    while (resultSet.next()) {
                        String year = resultSet.getString("Year");
                        String month = resultSet.getString("Month");
                        double commission = resultSet.getDouble("Commission");

                        commissionPerMonthList.add(new CommissionPerMonth(year, month, commission));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
