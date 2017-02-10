package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController {


    public void openAddSupplier(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("../views/addSupplierSample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Supplier");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }catch (Exception e) {
           e.printStackTrace();
        }
    }
}



//    public void handle(ActionEvent event) {
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
//            Stage stage = new Stage();
//            stage.setTitle("My New Stage Title");
//            stage.setScene(new Scene(root, 450, 450));
//            stage.show();
//            // Hide this current window (if this is what you want)
//            ((Node)(event.getSource())).getScene().getWindow().hide();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}