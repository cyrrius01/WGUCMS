/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class CustomerController implements Initializable {

    @FXML
    private TextField TextFieldCustomerId;
    @FXML
    private TextField TextFieldFirstName;
    @FXML
    private TextField TextFieldLastName;
    @FXML
    private TextField TextFieldPhone;
    @FXML
    private TextField TextFieldStreetAddress;
    @FXML
    private TextField TextFieldAptSte;
    @FXML
    private TextField TextFieldCity;
    @FXML
    private TextField TextFieldPostalCode;
    @FXML
    private TextField TextFieldCountry;
    @FXML
    private Button ButtonSave;
    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onButtonSave(ActionEvent event) {
    }

    @FXML
    private void onButtonCancel(ActionEvent event) {
    }

    @FXML
    private void onButtonDelete(ActionEvent event) {
    }
    
}
