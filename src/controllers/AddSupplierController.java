package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSupplierController {

    @FXML
    private TextField txfCompanyName, txfAddress, txfZipCode, txfCity, txfPhoneNumber, txfEmail;




    public void onClickAddSupplier(ActionEvent actionEvent) {

        String company_name = txfCompanyName.getText();
        String address = txfAddress.getText();
        String zipCode = txfZipCode.getText();
        String city = txfCity.getText();
        String phoneNumber = txfPhoneNumber.getText();
        String eMail = txfEmail.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {
            try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

                try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Supplier (Company_Name, Address, Zip_code, City, Phone_number, Email)VALUES(?,?,?,?,?,?)")) {
                    pstm.setString(1, company_name);
                    pstm.setString(2, address);
                    pstm.setString(3, zipCode);
                    pstm.setString(4, city);
                    pstm.setString(5, phoneNumber);
                    pstm.setString(6, eMail);

                    pstm.execute();

                    alert.setTitle("Supplier added");
                    alert.setContentText("The supplier is sucessfully added to database");
                    alert.showAndWait();
                    alert.close();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Adding Supplier failed!");
            error.setContentText("The supplier could not be added. Contact support.");
            error.showAndWait();
            error.close();
        }
        txfCompanyName.clear();
        txfAddress.clear();
        txfZipCode.clear();
        txfCity.clear();
        txfPhoneNumber.clear();
        txfEmail.clear();

    }
}
