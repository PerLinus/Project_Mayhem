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

        String firstName = txfFirstName.getText();
        String lastName = txfLastName.getText();
        String phoneNumber = txfPhoneNumber.getText();
        String eMail = txfEmail.getText();
        String address = txfAddress.getText();
        String city = txfCity.getText();
        String zipCode = txfZipCode.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer(First_name, Last_name, Phone_number, Email, Address, City, Zip_code)VALUES(?,?,?,?,?,?,?)")) {

                pstm.setString(1, firstName);
                pstm.setString(2, lastName);
                pstm.setString(3, phoneNumber);
                pstm.setString(4, eMail);
                pstm.setString(5, address);
                pstm.setString(6, city);
                pstm.setString(7, zipCode);

                pstm.execute();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
