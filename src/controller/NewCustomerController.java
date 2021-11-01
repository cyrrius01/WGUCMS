package controller;

import dao.DBQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * This controller class allows a user to create a new customer in the system
 */
public class NewCustomerController implements Initializable {

    public static String sendState;
    @FXML
    private TextField TextFieldCustomerId;
    @FXML
    private TextField TextFieldName;
    @FXML
    private TextField TextFieldPhone;
    @FXML
    private TextField TextFieldStreetAddress;
    @FXML
    private ComboBox<String> ComboBoxState;
    @FXML
    private TextField TextFieldPostalCode;
    @FXML
    private ComboBox<String> ComboBoxCountry;
    @FXML
    private Button ButtonSave;
    @FXML
    private Button ButtonCancel;


    private Connection conn;

    public static ObservableList options = DBQuery.options;
    public static ObservableList stateOptions = DBQuery.stateOptions;
    public static String countrySelected;
    public static String selected;

    /**
     * On initialize, the country combo box is populated
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {

        DBQuery.fillComboBox();

        ComboBoxCountry.setItems(options);

    }

    /**
     * This method takes the selected country from the combo box and sets the value to selected then fills the state combo box with the appropriate
     * 1st level divisions (states or provinces)
     *
     * @param event
     */
    @FXML
    private void onComboClick(ActionEvent event) {

        selected = ComboBoxCountry.getValue();
        System.out.println(selected);



        DBQuery.fillStateComboBox();
        ComboBoxState.setItems(stateOptions);
    }

    @FXML
    public void onStateComboClick(ActionEvent event) {

    }

    /**
     * On save, the fields are checked to make sure none are empty, if one or more is empty an error will pop up. Otherwise, a new customer object is created
     * and inserted into the appropriate database table
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void onButtonSave(ActionEvent event) throws IOException {

        //Name, Phone, Address, Country, State, Postal Code
        String sendName = TextFieldName.getText();
        String sendPhone = TextFieldPhone.getText();
        String sendAddress = TextFieldStreetAddress.getText();
        String sendCountry = ComboBoxCountry.getValue();
        sendState = ComboBoxState.getValue();
        String sendPostalCode = TextFieldPostalCode.getText();
        int sendDivisionID = DBQuery.findDivisionID(sendState);

        if(TextFieldName.getText().isEmpty() || TextFieldPhone.getText().isEmpty() || TextFieldStreetAddress.getText().isEmpty() || ComboBoxCountry.getSelectionModel().isEmpty() || ComboBoxState.getSelectionModel().isEmpty() || TextFieldPostalCode.getText().isEmpty()) {
            Alert blank = new Alert(Alert.AlertType.ERROR);
            blank.setHeaderText(null);
            blank.setContentText("You must complete all fields!");
            blank.showAndWait();
        }

        else {
            DBQuery.newCustomerSave(sendName, sendPhone, sendAddress, sendCountry, sendState, sendPostalCode, sendDivisionID);

            Alert blank = new Alert(Alert.AlertType.INFORMATION);
            blank.setHeaderText(null);
            blank.setContentText("Customer created!");
            blank.showAndWait();

            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

            Stage newStage = new Stage();
            newStage.setTitle(null);
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        }


    }

    /**
     * ON cancel the current window closes and returns the user to the main screen
     *
     * @param event
     * @throws IOException
     */
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


}
