package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

        try {
            try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

                PreparedStatement pstm = connection.prepareStatement("INSERT INTO Supplier (Company_Name, Address, Zip_code, City, Phone_number, Email)VALUES(?,?,?,?,?,?)");
                pstm.setString(1, company_name);
                pstm.setString(2, address);
                pstm.setString(3, zipCode);
                pstm.setString(4, city);
                pstm.setString(5, phoneNumber);
                pstm.setString(6, eMail);

                pstm.execute();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
