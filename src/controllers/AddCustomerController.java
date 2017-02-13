package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCustomerController {

    @FXML
    private TextField txfFirstName, txfLastName, txfPhoneNumber, txfEmail, txfAddress, txfCity, txfZipCode;

    public void onClickAddCustomer(ActionEvent actionEvent) {

        try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer(Firstname, Lastname, )")

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
