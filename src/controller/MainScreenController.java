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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class MainScreenController implements Initializable {

    public Button onManageSelected;
    @FXML
    private Hyperlink MonthHyperlink;
    @FXML
    private Hyperlink WeekHyperlink;
    @FXML
    private Hyperlink AllHyperlink;
    @FXML
    private Button NewAppointmentButton;
    @FXML
    private Button ManageCustomerButton;
    @FXML
    private Button ReportsButton;
    @FXML
    private Button ExitButton;
    @FXML
    private TableView<Appointment> MainScreenTableView;
    @FXML
    private TableColumn<Appointment, String> ContactTableColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptIDTableColumn;
    @FXML
    private TableColumn<Appointment, String> TitleTableColumn;
    @FXML
    private TableColumn<Appointment, String> DescriptionTableColumn;
    @FXML
    private TableColumn<Appointment, String> LocationTableColumn;
    @FXML
    private TableColumn<Appointment, String> TypeTableColumn;
    @FXML
    private TableColumn<Appointment, Timestamp> StartDateTimeTableColumn;
    @FXML
    private TableColumn<Appointment, Timestamp> EndDateTimeTableColumn;
    @FXML
    private TableColumn<Appointment, Integer> CustomerIDTableColumn;
    @FXML
    private TableColumn<Appointment, Integer>UserIDTableColumn;
    
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
    private static int searchId;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        clearForm();

        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        NewAppointmentButton.setText(languageRB.getString("NewAppointment"));
        ManageCustomerButton.setText(languageRB.getString("ManageCustomer"));
        ReportsButton.setText(languageRB.getString("Reports"));
        ExitButton.setText(languageRB.getString("Exit"));
        AllHyperlink.setText(languageRB.getString("All"));
        MonthHyperlink.setText(languageRB.getString("Month"));
        WeekHyperlink.setText(languageRB.getString("Week"));

        String apptQuery = DBQuery.getAllAppointments();

        populateTV(apptQuery);
    }    

    @FXML
    private void OnMonthHyperlink(ActionEvent event) {
        
        clearForm();

        String apptQuery = DBQuery.getMonthAppointments();

        populateTV(apptQuery);

    }

    @FXML
    private void OnWeekHyperlink(ActionEvent event) {
        clearForm();
        
        String apptQuery = DBQuery.getWeekAppointments();
        populateTV(apptQuery);
    }

    @FXML
    private void OnAllHyperlink(ActionEvent event) {
        clearForm();

        String apptQuery = DBQuery.getAllAppointments();
        
        populateTV(apptQuery);
    }

    @FXML
    private void onNewAppointmentClick(ActionEvent event) throws IOException {
        clearForm();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        String url = "/view/AppointmentScreen.fxml";
        stageSetup(url);
    }

    @FXML
    private void onManageCustomerClick(ActionEvent event) throws IOException {
        
        clearForm();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        
        String url = "/view/CustomerSearch.fxml";
        stageSetup(url);
        
    }

    @FXML
    private void OnReportsClick(ActionEvent event) throws IOException {
        clearForm();
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        String url = "/view/Reports.fxml";
        stageSetup(url);
        
    }

    @FXML
    private void OnExitClick(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        System.out.println("Main Exit Clicked");
        System.exit(0);
   
    }
    
    private void clearForm(){
        allAppointments.clear();
        monthAppointments.clear();
        MainScreenTableView.getItems().clear();
        MainScreenTableView.refresh();
    }
    
    private void stageSetup(String url) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(url));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    
    
    private void populateTV(String apptQuery) {
        Appointment.clearAppointments();
        clearForm();
        Statement apptStatement = DBQuery.getStatement();
        
        try {
            apptStatement.execute(apptQuery);
            ResultSet apptRs = apptStatement.getResultSet();

            while(apptRs.next()) {



                int apptAppointment_ID = apptRs.getInt("Appointment_ID");
                String apptTitle = apptRs.getString("Title");
                String apptDescription = apptRs.getString("Description");
                String apptLocation = apptRs.getString("Location");
                String apptContact_Name = apptRs.getString("Contact_Name");
                String apptType = apptRs.getString("Type");



                Timestamp ts = apptRs.getTimestamp("Start");
                ///LocalDateTime ldt = ts.toLocalDateTime();
                //ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
                //ZonedDateTime tzdt = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
                //String startDateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(tzdt);

                //LocalDateTime sldt = tzdt.toLocalDateTime();

                Timestamp tse = apptRs.getTimestamp("End");
                //LocalDateTime ldte = tse.toLocalDateTime();
                //ZonedDateTime zdte = ldte.atZone(ZoneId.of("UTC"));
                //ZonedDateTime zdtet = zdte.withZoneSameInstant(ZoneId.of("America/New_York"));
                //String endDateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zdtet);
                //LocalDateTime ldtet = zdtet.toLocalDateTime();

                int apptCustomer_ID = apptRs.getInt("Customer_ID");
                int apptUser_ID = apptRs.getInt("User_ID");


                Appointment newAppointment = new Appointment(apptAppointment_ID, apptTitle, apptDescription, apptLocation, apptContact_Name, apptType,
                        ts.toLocalDateTime(), tse.toLocalDateTime(), apptCustomer_ID, apptUser_ID);



                Appointment.getAllAppointments().add(newAppointment);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        MainScreenTableView.setItems(Appointment.getAllAppointments());
        ContactTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptContact_Name"));
        ApptIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptAppointment_ID"));
        TitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        DescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        LocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        TypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        StartDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        EndDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
        CustomerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomer_ID"));
        UserIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptUser_ID"));
    }

    @FXML
    private void onManageSelectedButton(ActionEvent event) throws IOException {
        Appointment appointment = MainScreenTableView.getSelectionModel().getSelectedItem();

        // need if statement to ensure customer !null
        if (MainScreenTableView.getSelectionModel().isEmpty()) {
            Alert blank = new Alert(Alert.AlertType.ERROR);
            blank.setHeaderText(null);
            blank.setContentText("You must make a selection first!");
            blank.showAndWait();
        } else {

            ManageAppointmentController.football(appointment);


            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            Parent root = FXMLLoader.load(getClass().getResource("/view/ManageAppointment.fxml"));

            Stage newStage = new Stage();
            newStage.setTitle(null);
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        }
    }
}
