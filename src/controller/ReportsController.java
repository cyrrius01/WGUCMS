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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * FXML Controller class controls the reports window and displays three different reports
 *
 * @author Keith A Graham
 */
public class ReportsController implements Initializable {

    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonExit;
    @FXML
    private ComboBox ComboBoxType;
    @FXML
    private ComboBox ComboBoxMonth;
    @FXML
    private Label customerApptsTotal;
    @FXML
    private ComboBox ComboBoxContactName;
    @FXML
    private TableView TableViewAppointments;
    @FXML
    private TableColumn<?, Integer > ColumnApptID;
    @FXML
    private TableColumn<?, String> ColumnTitle;
    @FXML
    private TableColumn<?, String> ColumnType;
    @FXML
    private TableColumn<?, String> ColumnDescription;
    @FXML
    private TableColumn<?, Timestamp> ColumnStartDateTime;
    @FXML
    private TableColumn<?, Timestamp> ColumnEndDateTime;
    @FXML
    private TableColumn<?, Integer> ColumnCustomerID;
    @FXML
    private Label LabelActiveUsers;

    private String type;
    private String month;

    public static ObservableList options = FXCollections.observableArrayList();

    /**
     * Initializes the controller class and sets default values to combo boxes and using queries populates the active users report.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ComboBoxContactName.getItems().addAll(DBQuery.contacts());
        LabelActiveUsers.setText(Integer.toString(DBQuery.activeUsers()));
        ComboBoxType.getItems().addAll("Scrum", "Presentation", "Planning Session", "De-Briefing");
        ComboBoxMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }

    /**
     * The back button allows the user to return to the main menu.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void onButtonBack(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
    }


    /**
     * This method allows a user to select a contact and a query runs to populate the tableview showing all appointments for that contact
     *
     * @param actionEvent
     */
    public void onComboBoxContactName(ActionEvent actionEvent) {

        // getselecteditem which is contact name
        // run query contactIDLookup()
        // this will get the ID to use in new query

        String contactName = ComboBoxContactName.getSelectionModel().getSelectedItem().toString();
        int contactID = DBQuery.contactIDLookup(contactName);
        ResultSet rs = DBQuery.contactAppointments(contactID);
        Appointment.clearAppointments();
        try{
            while(rs.next()) {
                int apptAppointment_ID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptType = rs.getString("Type");
                String apptDescription = rs.getString("Description");
                Timestamp ts = rs.getTimestamp("Start");
                Timestamp tse = rs.getTimestamp("End");
                int apptCustomer_ID = rs.getInt("Customer_ID");

                Appointment newAppointment = new Appointment(apptAppointment_ID, apptTitle, apptType, apptDescription, ts.toLocalDateTime(), tse.toLocalDateTime(), apptCustomer_ID);

                Appointment.getAllAppointments().add(newAppointment);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        TableViewAppointments.setItems(Appointment.getAllAppointments());
        ColumnApptID.setCellValueFactory(new PropertyValueFactory<>("apptAppointment_ID"));
        ColumnTitle.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        ColumnType.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        ColumnStartDateTime.setCellValueFactory(new PropertyValueFactory<> ("apptStart"));
        ColumnEndDateTime.setCellValueFactory(new PropertyValueFactory<> ("apptEnd"));
        ColumnCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomer_ID"));

    }

    /**
     * This method checks that both month and type are selected, if not, nothing occurs. If so, a query is run and returns the count of appointments by month
     * and type
     *
     * @param actionEvent
     */
    public void onComboBoxMonth(ActionEvent actionEvent) {
        if(ComboBoxType.getSelectionModel().isEmpty()) {
            return;
        } else {
            month = ComboBoxMonth.getSelectionModel().getSelectedItem().toString();
            type = ComboBoxType.getSelectionModel().getSelectedItem().toString();
            int count = DBQuery.getMonthTypeCount(type, month);
            customerApptsTotal.setText(String.valueOf(count));
        }

    }

    /**
     * This method checks that both month and type are selected, if not, nothing occurs. If so, a query is run and returns the count of appointments by month
     * and type
     *
     * @param actionEvent
     */
    public void onComboBoxType(ActionEvent actionEvent) {
        if(ComboBoxMonth.getSelectionModel().isEmpty()) {
            return;
        } else {
            type = ComboBoxType.getSelectionModel().getSelectedItem().toString();
            month = ComboBoxMonth.getSelectionModel().getSelectedItem().toString();
            int count = DBQuery.getMonthTypeCount(type, month);
            customerApptsTotal.setText(String.valueOf(count));
        }


    }
}
