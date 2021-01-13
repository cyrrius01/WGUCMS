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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button ButtonSearch;
    @FXML
    private ComboBox ComboBoxSearch;
    @FXML
    private Button ButtonNew;
    @FXML
    private TableView<?> TableViewCustomer;
    @FXML
    private TableColumn<?, ?> TableColumnFirstName;
    @FXML
    private TableColumn<?, ?> TableColumnLastName;
    @FXML
    private TableColumn<?, ?> TableColumnPhone;
    private int intResults;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());

        
        ComboBoxSearch.getItems().addAll(languageRB.getString("Name"), languageRB.getString("Phone"));
        
        ButtonSelect.setText(languageRB.getString("Select"));
        ButtonCancel.setText(languageRB.getString("Cancel"));
        
        ButtonSearch.setText(languageRB.getString("Search"));
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
        
  
        
    }

    @FXML
    private void onButtonNew(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
        
    }
    
}
