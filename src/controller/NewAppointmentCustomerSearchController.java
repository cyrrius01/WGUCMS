/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class NewAppointmentCustomerSearchController implements Initializable {

    @FXML
    private Button ButtonSelect;
    @FXML
    private Button ButtonCancel;
    @FXML
    private TextField TextFieldSearch;
    @FXML
    private Button ButtonSearch;
    @FXML
    private ComboBox<?> ComboBoxSearch;
    @FXML
    private TableView<?> TableViewResults;
    @FXML
    private TableColumn<?, ?> TableColumnFirstName;
    @FXML
    private TableColumn<?, ?> TableColumnLastName;
    @FXML
    private TableColumn<?, ?> TableColumnPhone;
    @FXML
    private Label LabelResults;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onButtonSelect(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/NewAppointmentExistingCustomer.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
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
    
}
