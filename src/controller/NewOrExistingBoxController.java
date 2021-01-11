package controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class NewOrExistingBoxController implements Initializable {

    @FXML
    private Button NewButton;
    @FXML
    private Button ExistingButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextArea NewOrExistingTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        NewOrExistingTextArea.setText(languageRB.getString("NewOrExistingCustomer"));
        NewButton.setText(languageRB.getString("New"));
        ExistingButton.setText(languageRB.getString("Existing"));
        CancelButton.setText(languageRB.getString("Cancel"));
    }    

    @FXML
    private void onNewCustomer(ActionEvent event) {
    }

    @FXML
    private void onExistingCustomer(ActionEvent event) {
    }

    @FXML
    private void onCancelCustomer(ActionEvent event) {
    }
    
}
