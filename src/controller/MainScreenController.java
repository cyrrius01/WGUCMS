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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Keith A Graham
 */
public class MainScreenController implements Initializable {

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
    private TableColumn<Appointment, LocalDate> DateTableColumn;
    @FXML
    private TableColumn<Appointment, LocalTime> TimeTableColumn;
    @FXML
    private TableColumn<Appointment, String> CustomerTableColumn;
    
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
    private static int searchId;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        clearForm();

        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        DateTableColumn.setText(languageRB.getString("Date"));
        TimeTableColumn.setText(languageRB.getString("Time"));
        CustomerTableColumn.setText(languageRB.getString("Customer"));
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
        Statement apptStatement = DBQuery.getStatement();
        
        try {
            apptStatement.execute(apptQuery);
            ResultSet apptRs = apptStatement.getResultSet();

            while(apptRs.next()) {

                Timestamp apptTime = apptRs.getTimestamp("Time");
                Timestamp apptDate = apptRs.getTimestamp("Date");
                String apptCustomer = apptRs.getString("Customer_Name");

                System.out.println("time"+ apptTime + " " + "Date" + apptDate + " " + "Customer" + apptCustomer);


                LocalDateTime ldt = apptTime.toLocalDateTime();
                ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("GMT"));
                System.out.println("zdt = " + zdt);
                ZonedDateTime targetZdt = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
                System.out.println("targetZdt = " + targetZdt); // this is where the hour is "lost", the system is doing GMT - 5 here instead of 4 for daylight saving time
                LocalDateTime cvrtLdt = targetZdt.toLocalDateTime();
                LocalTime ltSend = cvrtLdt.toLocalTime();

                Appointment newAppointment = new Appointment(apptDate.toLocalDateTime().toLocalDate(),
                        ltSend, apptCustomer);

                // check to see if appointment already exists
                if(!Appointment.getAllAppointments().contains(newAppointment)) {
                    Appointment.addAppointment(newAppointment);
                }   
                else {
                    break;
                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        MainScreenTableView.setItems(Appointment.getAllAppointments());
        DateTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptDate"));
        TimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptTime"));
        CustomerTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomer"));
    }
}
