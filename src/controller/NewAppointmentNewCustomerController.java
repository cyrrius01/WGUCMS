/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class NewAppointmentNewCustomerController implements Initializable {

    @FXML
    private ComboBox ComboBoxAppointmentType;
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
    private ComboBox ComboBoxTime;

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
        
        ComboBoxAppointmentType.getItems().addAll("Presentation", "Scrum");
        
        // this next one is going to be tricky, maybe query Appointment table with respective date
        // create an array of times on the hour
        // check for any times that are in the table already
        // and create a second array with those hours removed
        // and display those as the combo box options
        // ComboBoxTime.getItems().addAll()
    }    

    @FXML
    private void onComboBoxAppointmentType(ActionEvent event) {
    }

    @FXML
    private void onButtonSave(ActionEvent event) {
        
        // this on save event will collect all info from the fields and selection and 
        // save to either the appointment table or customer table
        // maybe city/state/country table, depends
        
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

    @FXML
    private void onDatePickerNewAppointment(ActionEvent event) {
    }

    @FXML
    private void onComboBoxTime(ActionEvent event) {
    }
    
}
