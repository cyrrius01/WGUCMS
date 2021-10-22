package wgucms;

import dao.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;


public class WguCMS extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) throws SQLException {

       
        //Locale.setDefault(new Locale("fr")); // used to test French translation on login page
        launch(args);
        // anything past launch(args); only occurs after all windows to the app are closed
        
        DBConnection.closeConnection();
    }
    
}
