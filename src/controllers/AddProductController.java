package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddProductController {

    List<Supplier> supplierList = new ArrayList<>();

    @FXML
    private TextField txfProductName, txfCommission;
    @FXML
    private TextArea txfDescription;
    @FXML
    private ComboBox cbChooseSupplier;

    public void initialize() {

        loadSupplierList();

        ObservableList<Supplier> observerSList = FXCollections.observableArrayList(supplierList);
        cbChooseSupplier.getItems().clear();
        cbChooseSupplier.setItems(observerSList);
        cbChooseSupplier.getSelectionModel().selectFirst();


    }

    public void loadSupplierList() {

        try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (Statement stm = connection.createStatement()) {

                try (ResultSet rs = stm.executeQuery("SELECT * FROM Supplier")) {

                    while (rs.next()) {

                        supplierList.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onClickAddProduct(ActionEvent actionEvent) {

        String product_name = txfProductName.getText();
        String comission = txfCommission.getText();;
        String info = txfDescription.getText();


        try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Product (Supplier_ID, Product_Name, Commission, Info)VALUES(?,?,?,?)")) {

                Supplier supplier = (Supplier) cbChooseSupplier.getSelectionModel().getSelectedItem();
                pstm.setInt(1, supplier.getSupplierID());
                pstm.setString(2, product_name);
                pstm.setString(3, comission);
                pstm.setString(4, info);

                pstm.execute();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


