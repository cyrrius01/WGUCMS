/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DBQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class controls the window for a selected customer
 *
 * @author Keith A Graham
 */
public class CustomerController implements Initializable {

    @FXML
    private TextField TextFieldCustomerId;
    @FXML
    private TextField TextFieldName;
    @FXML
    private TextField TextFieldPhone;
    @FXML
    private TextField TextFieldStreetAddress;
    @FXML
    private ComboBox ComboBoxState;
    @FXML
    private TextField TextFieldPostalCode;
    @FXML
    private ComboBox ComboBoxCountry;
    @FXML
    private Button ButtonSave;
    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonDelete;

    public Customer newCustomer;



    static int id;
    static String name;
    static String address;
    static String postal;
    static String phone;
    static String state;
    static String country;

    public static ObservableList options = DBQuery.options;
    public static ObservableList stateOptions = DBQuery.stateOptions;
    public static String countrySelected;
    public static String selected;

    /**
     * This football brings in the needed data to populate the customer form during the call to initialize.
     * @param customer
     */
    public static void football(Customer customer){
        Customer newCustomer = customer;

        id = newCustomer.getCustomer_ID();
        name = newCustomer.getCustomer_Name();
        address = newCustomer.getAddress();
        postal = newCustomer.getPostal_Code();
        phone = newCustomer.getPhone();
        state = newCustomer.getDivision();
        country = newCustomer.getCountry();

    }

    /**
     * All fields are set to their respective values from the football values that were passed in above.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBQuery.fillComboBox();

        TextFieldCustomerId.setText(Integer.toString(id));
        TextFieldName.setText(name);
        TextFieldStreetAddress.setText(address);
        TextFieldPostalCode.setText(postal);
        TextFieldPhone.setText(phone);
        ComboBoxCountry.setItems(options);

        // we have the country, just need to find that value in the list options
        int countryIndex = options.indexOf(country);
        ComboBoxCountry.getSelectionModel().select(countryIndex);

        selected = (String) ComboBoxCountry.getValue();

        // now need to get selected, based on selected populate statecombobox, then set based on index again
        DBQuery.NewfillStateComboBox();
        ComboBoxState.setItems(stateOptions);

        int stateIndex = stateOptions.indexOf(state);
        ComboBoxState.getSelectionModel().select(stateIndex);

    }

    /**
     * On save, any changes made are added to the customer object and then a query runs to update the respective database table(s).
     * @param event
     */
    @FXML
    private void onButtonSave(ActionEvent event) {


        if(!TextFieldName.getText().equals(name) || !TextFieldStreetAddress.getText().equals(address) || !TextFieldPostalCode.getText().equals(postal) ||
                !TextFieldPhone.getText().equals(phone) || !ComboBoxState.getSelectionModel().getSelectedItem().equals(state) ||
                !ComboBoxCountry.getSelectionModel().getSelectedItem().equals(country))
        {

            String newName = TextFieldName.getText();
            String newAddress = TextFieldStreetAddress.getText();
            String newPostal = TextFieldPostalCode.getText();
            String newPhone = TextFieldPhone.getText();
            String newState = (String) ComboBoxState.getValue();
            String newCountry = (String) ComboBoxCountry.getValue();

            DBQuery.updateCustomer(id, newName, newAddress, newPostal, newPhone, newState, newCountry);

            Alert blank = new Alert(Alert.AlertType.INFORMATION);
            blank.setHeaderText(null);
            blank.setContentText("Changes saved.");
            blank.showAndWait();

        } else {
            Alert blank = new Alert(Alert.AlertType.INFORMATION);
            blank.setHeaderText(null);
            blank.setContentText("No changes to save.");
            blank.showAndWait();
        }


    }

    /**
     * On cancel, any changes made are tossed out and the form is closed returning the user to the Customer Search form.
     * @param event
     * @throws IOException
     */
    @FXML
    private void onButtonCancel(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerSearch.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
    }

    /**
     * On delete the user is prompted to confirm they wish to delete the customer. If Ok is pressed, a query runs to delete the customer from the
     * respective database tables and the form closes on completion.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void onButtonDelete(ActionEvent event) throws IOException {

        Alert blank = new Alert(Alert.AlertType.CONFIRMATION, "Press OK to delete the customer.");

        blank.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                DBQuery.deleteCustomer(id);

                Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                newAlert.setHeaderText(null);
                newAlert.setContentText("Customer deleted successfully.");
                newAlert.showAndWait();

                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/CustomerSearch.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage newStage = new Stage();
                newStage.setTitle(null);
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.show();
            }
        });

    }


    public void onComboCountry(ActionEvent actionEvent) {

    }
}
