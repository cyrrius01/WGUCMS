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

    public void initialize(URL url, ResourceBundle rb) {

        DBQuery.fillComboBox();

        ComboBoxCountry.setItems(options);

        // step 2 populate state dropdown based on country selection
        // case when country = us, etc


        // step 3 onSave create customer record in customers table, ensuring autocreation of ID
    }

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
