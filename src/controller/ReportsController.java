package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class ReportsController implements Initializable {

    @FXML
    private Button ButtonCancel;
    @FXML
    private Hyperlink HyperlinkApptByMonth;
    @FXML
    private Hyperlink HyperlinkConsultantSched;
    @FXML
    private Hyperlink HyperlinkNumActiveConsult;
    @FXML
    private Button ButtonExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onButtonCancel(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
    }

    @FXML
    private void onHyperlinkApptByMonth(ActionEvent event) {
    }

    @FXML
    private void onHyperlinkConsultantSched(ActionEvent event) {
    }

    @FXML
    private void onHyperlinkNumActiveConsult(ActionEvent event) {
    }

    @FXML
    private void onButtonExit(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        
        System.out.println("Main Exit Clicked");
        System.exit(0);
        
    }
    
}
