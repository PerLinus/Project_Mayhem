package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(validateUserInput()) {

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

                    alert.setTitle("New customer added");
                    alert.setContentText("Customer "+txfFirstName.getText()+" "+txfLastName.getText()+" is sucessfully added to database");
                    alert.showAndWait();
                    alert.close();

                }

            } catch (SQLException e) {
                e.printStackTrace();
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Database error!");
                error.setContentText("Customer "+txfFirstName.getText()+" "+txfLastName.getText()+" could not be added. Contact support!");
                error.showAndWait();
                error.close();
            }

            txfFirstName.clear();
            txfLastName.clear();
            txfPhoneNumber.clear();
            txfEmail.clear();
            txfAddress.clear();
            txfCity.clear();
            txfZipCode.clear();
        } else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid input");
            error.setContentText("All fields must be filled!");
            error.showAndWait();
            error.close();
        }
    }

    public boolean validateUserInput(){
        if(txfFirstName.getText().trim().length() == 0){
            return false;
        } else if(txfLastName.getText().trim().length() == 0){
            return false;
        } else if (txfPhoneNumber.getText().trim().length() == 0){
            return false;
        } else if(txfEmail.getText().trim().length() == 0){
            return false;
        } else if(txfAddress.getText().trim().length() == 0){
            return false;
        } else if(txfCity.getText().trim().length() == 0){
            return false;
        } else if(txfZipCode.getText().trim().length() == 0){
            return false;
        }else{
            return true;
        }
    }
}
