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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class AppointmentController implements Initializable {

    @FXML
    private ComboBox<?> ComboBoxAppointmentType;
    @FXML
    private DatePicker DatePickerNewAppointment;
    @FXML
    private ComboBox<?> ComboBoxTime;
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
    private Button ButtonExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onComboBoxAppointmentType(ActionEvent event) {
    }

    @FXML
    private void onDatePickerNewAppointment(ActionEvent event) {
    }

    @FXML
    private void onComboBoxTime(ActionEvent event) {
    }

    @FXML
    private void onButtonSave(ActionEvent event) {
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
    private void onButtonExit(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        
        System.out.println("Main Exit Clicked");
        System.exit(0);
        
    }
    
}
