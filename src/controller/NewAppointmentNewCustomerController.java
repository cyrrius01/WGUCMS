/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class NewAppointmentNewCustomerController implements Initializable {

    @FXML
    private ComboBox<?> ComboBoxAppointmentType;
    @FXML
    private DatePicker DatePickerNewAppointment;
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
    private TextField TextFieldState;
    @FXML
    private TextField TextFieldCountry;
    @FXML
    private Button ButtonSave;
    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonExit;
    @FXML
    private ComboBox<?> ComboBoxTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        ComboBoxAppointmentType.setPromptText(languageRB.getString("AppointmentType"));
        DatePickerNewAppointment.setPromptText(languageRB.getString("Date"));
        ComboBoxTime.setPromptText(languageRB.getString("Time"));
        TextFieldFirstName.setPromptText(languageRB.getString("FirstName"));
        TextFieldLastName.setPromptText(languageRB.getString("LastName"));
        TextFieldPhone.setPromptText(languageRB.getString("Phone"));
        TextFieldStreetAddress.setPromptText(languageRB.getString("StreetAddress"));
        TextFieldAptSte.setPromptText(languageRB.getString("AptSte"));
        TextFieldCity.setPromptText(languageRB.getString("City"));
        TextFieldState.setPromptText(languageRB.getString("State"));
        TextFieldCountry.setPromptText(languageRB.getString("Country"));
        ButtonSave.setText(languageRB.getString("Save"));
        ButtonCancel.setText(languageRB.getString("Cancel"));
        ButtonExit.setText(languageRB.getString("Exit"));
    }    

    @FXML
    private void onComboBoxAppointmentType(ActionEvent event) {
    }

    @FXML
    private void onButtonSave(ActionEvent event) {
    }

    @FXML
    private void onButtonCancel(ActionEvent event) {
    }

    @FXML
    private void onButtonExit(ActionEvent event) {
    }

    @FXML
    private void onDatePickerNewAppointment(ActionEvent event) {
    }

    @FXML
    private void onComboBoxTime(ActionEvent event) {
    }
    
}
