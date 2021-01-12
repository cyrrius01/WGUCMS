package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class CustomerSearchController implements Initializable {

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

        
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        ButtonSelect.setText(languageRB.getString("Select"));
        ButtonCancel.setText(languageRB.getString("Cancel"));
        TextFieldSearch.setPromptText(languageRB.getString("SearchText"));
        LabelResults.setText(languageRB.getString("Results"));
        ButtonSearch.setText(languageRB.getString("SearchButton"));
        ComboBoxSearch.setPromptText(languageRB.getString("SearchBy"));
        ButtonNew.setText(languageRB.getString("New"));
        
    }    

    @FXML
    private void onButtonSelect(ActionEvent event) {
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
