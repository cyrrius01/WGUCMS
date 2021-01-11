package controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class ManageCustomerController implements Initializable {

    @FXML
    private Button ButtonSelect;
    @FXML
    private Button ButtonCancel;
    @FXML
    private TextField TextFieldSearch;
    @FXML
    private TextArea TextAreaResults;
    @FXML
    private Label LabelResults;
    @FXML
    private Button ButtonSearch;
    @FXML
    private ComboBox ComboBoxSearch;
    @FXML
    private Button ButtonNew;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxSearch.getItems().addAll("Name", "Phone Number");

        
        /**ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        ButtonSelect.setText(languageRB.getString("Select"));
        ButtonCancel.setText(languageRB.getString("Cancel"));
        TextFieldSearch.setText(languageRB.getString("SearchText"));
        LabelResults.setText(languageRB.getString("Results"));
        ButtonSearch.setText(languageRB.getString("SearchButton"));
        //ComboBoxSearch.setText(languageRB.getString("SearchBy"));
        ButtonNew.setText(languageRB.getString("New"));
        * */
    }    

    @FXML
    private void onButtonSelect(ActionEvent event) {
    }

    @FXML
    private void onButtonCancel(ActionEvent event) {
    }

    @FXML
    private void onButtonSearch(ActionEvent event) {
    }

    @FXML
    private void onComboBoxSearch(ActionEvent event) {
        
        String promptText = ComboBoxSearch.getValue().toString();
        TextFieldSearch.setPromptText(promptText);
        
    }

    @FXML
    private void onButtonNew(ActionEvent event) {
    }
    
}
