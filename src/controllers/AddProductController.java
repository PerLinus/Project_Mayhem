package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        String commissionText = txfCommission.getText();
        String info = txfDescription.getText();

        double commission = Double.parseDouble(commissionText);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try (Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Product (Supplier_ID, Product_Name, Commission, Info)VALUES(?,?,?,?)")) {

                Supplier supplier = (Supplier) cbChooseSupplier.getSelectionModel().getSelectedItem();
                pstm.setInt(1, supplier.getSupplierID());
                pstm.setString(2, product_name);
                pstm.setDouble(3, (commission / 100));
                pstm.setString(4, info);

                pstm.execute();

                alert.setTitle("Product added");
                alert.setContentText("Your product is sucessfully added to database");
                alert.showAndWait();
                alert.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Product error!");
            error.setContentText("Your product could not be added. Contact support.");
            error.showAndWait();
            error.close();
        }
    }
}


