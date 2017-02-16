package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * Created by PereZ on 2017-02-14.
 */
public class LogInWindowController {

    @FXML
    private TextField txfUserName;

    @FXML
    private PasswordField pwfPassword;



    public void onClickLogIn(ActionEvent actionEvent) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_Mayhem?useSSL=false", "root", "root")) {

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM LogIn WHERE LogIn.UserName = ?")) {
                preparedStatement.setString(1, txfUserName.getText());

                try (ResultSet result = preparedStatement.executeQuery()) {

                    while (result.next()) {

                        String userName = result.getString(1);
                        String password = result.getString(2);
                        boolean accsess = result.getBoolean(3);

                        if (password.equals(pwfPassword.getText()) && userName.equals(txfUserName.getText())) {

                            if (accsess == true) {

                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("../views/mainWindowSample.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Admin Window");
                                    Scene scene = new Scene(root, 800, 600);
                                    scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                                    stage.setScene(scene);
                                    stage.setResizable(false);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("../views/biddingViewSamle.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("User Window");
                                    stage.setScene(new Scene(root, 600, 400));
                                    stage.setResizable(false);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
