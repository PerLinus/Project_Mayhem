package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CustomerHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerListController {

    private List<CustomerHistory> customer_historyList = new ArrayList<>();

    @FXML
    private TableColumn tcId, tcFirstName, tcLastName, tcTotalPurchase;
    @FXML
    private TableView tvCustomerList;


    public void initialize(){

        loadCustomerListView();

        ObservableList<CustomerHistory> list = FXCollections.observableArrayList(customer_historyList);
        tcId.setCellValueFactory(new PropertyValueFactory<CustomerHistory, Integer >("customerID"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<CustomerHistory, String >("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<CustomerHistory, String >("lastName"));
        tcTotalPurchase.setCellValueFactory(new PropertyValueFactory<CustomerHistory, Double >("totalPurchase"));
        tvCustomerList.setItems(list);

    }

    public void loadCustomerListView(){

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM customer_history")) {

                    while (resultSet.next()) {
                        int customerID = resultSet.getInt("Customer_ID");
                        String firstName= resultSet.getString("First_Name");
                        String lastName = resultSet.getString("Last_Name");
                        double totalpurchases = resultSet.getDouble("TotalPurchases");

                        customer_historyList.add(new CustomerHistory(customerID, firstName, lastName, totalpurchases));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
