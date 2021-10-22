package controller;

import dao.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private Button ButtonNew;
    @FXML
    private TableView<Customer> TableViewCustomer;
    @FXML
    private TableColumn<Customer, Integer> TableColumnCustomerID;
    @FXML
    private TableColumn<Customer, String> TableColumnName;
    @FXML
    private TableColumn<Customer, String> TableColumnAddress;
    @FXML
    private TableColumn<Customer, String> TableColumnPostCode;
    @FXML
    private TableColumn<Customer, String> TableColumnPhone;
    @FXML
    private TableColumn<Customer, String> TableColumn1stLevDiv;
    @FXML
    private TableColumn<Customer, String> TableColumnCountry;

    private int intResults;


    public ObservableList<Appointment> allCustomers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());

        ButtonSelect.setText(languageRB.getString("Select"));
        ButtonCancel.setText(languageRB.getString("Cancel"));

        ButtonNew.setText(languageRB.getString("New"));

        String apptQuery = DBQuery.getAllCustomers();
        populateTV(apptQuery);


    }    



    @FXML
    private void onButtonSelect(ActionEvent event) throws IOException {

        Customer customer = TableViewCustomer.getSelectionModel().getSelectedItem();

        // need if statement to ensure customer !null
        if(TableViewCustomer.getSelectionModel().isEmpty()) {
            Alert blank = new Alert(Alert.AlertType.ERROR);
            blank.setHeaderText(null);
            blank.setContentText("You must make a selection first!");
            blank.showAndWait();
        } else {
            CustomerController.football(customer);

            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            Parent root = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));

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



    @FXML
    private void onButtonNew(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/NewCustomer.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
        
    }
    private void populateTV(String apptQuery) {
        Customer.getAllCustomers().clear();
        Statement apptStatement = DBQuery.getStatement();

        try {
            apptStatement.execute(apptQuery);
            ResultSet apptRs = apptStatement.getResultSet();

            while(apptRs.next()) {

                int Customer_ID = apptRs.getInt("Customer_ID");
                String Customer_Name = apptRs.getString("Customer_Name");
                String Address = apptRs.getString("Address");
                String Postal_Code = apptRs.getString("Postal_Code");
                String Phone = apptRs.getString("Phone");
                String Division = apptRs.getString("Division");
                String Country = apptRs.getString("Country");

                Customer newCustomer = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country);
                Customer.addCustomer(newCustomer);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        TableViewCustomer.setItems(Customer.getAllCustomers());

        TableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        TableColumnName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        TableColumnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        TableColumnPostCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        TableColumnPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        TableColumn1stLevDiv.setCellValueFactory(new PropertyValueFactory<>("Division"));
        TableColumnCountry.setCellValueFactory(new PropertyValueFactory<>("Country"));
    }



}
