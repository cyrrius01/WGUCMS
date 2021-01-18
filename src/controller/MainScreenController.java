package controller;

import dao.DBQuery;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
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
    
    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }
    public int getSearchId() {
        return this.searchId;
    }

    public static void receiver(int userId){
        searchId = userId;     
    }
    
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


        String apptQuery = "SELECT CAST(apt.start AS DATE) AS 'Date', CAST(apt.start AS TIME) AS 'Time', cs.customerName"
            + " FROM U04jTC.appointment apt JOIN U04jTC.customer cs JOIN U04jTC.user us ON apt.customerId = cs.customerId AND apt.userId = us.userId "
            + "WHERE us.userId = " + searchId;                

        populateTV(apptQuery);
    }    

    @FXML
    private void OnMonthHyperlink(ActionEvent event) {
        
        clearForm();
        
        String apptQuery = "SELECT CAST(apt.start AS DATE) AS 'Date', CAST(apt.start AS TIME) AS 'Time', cs.customerName"
            + " FROM U04jTC.appointment apt JOIN U04jTC.customer cs JOIN U04jTC.user us ON apt.customerId = cs.customerId AND apt.userId = us.userId "
            + "WHERE us.userId = " + searchId + " AND apt.start >= now() AND apt.start <= LAST_DAY(now())";                

        populateTV(apptQuery);

    }

    @FXML
    private void OnWeekHyperlink(ActionEvent event) {
        clearForm();
        
        String apptQuery = "SELECT CAST(apt.start AS DATE) AS 'Date', CAST(apt.start AS TIME) AS 'Time', cs.customerName"
                + " FROM U04jTC.appointment apt JOIN U04jTC.customer cs JOIN U04jTC.user us ON apt.customerId = cs.customerId AND apt.userId = us.userId "
                + "WHERE us.userId = " + searchId + " AND apt.start >= now() AND apt.start <= DATE(NOW() + INTERVAL(7-DAYOFWEEK(NOW())) DAY)";                

        populateTV(apptQuery);
    }

    @FXML
    private void OnAllHyperlink(ActionEvent event) {
        clearForm();
     
        String apptQuery = "SELECT CAST(apt.start AS DATE) AS 'Date', CAST(apt.start AS TIME) AS 'Time', cs.customerName"
                + " FROM U04jTC.appointment apt JOIN U04jTC.customer cs JOIN U04jTC.user us ON apt.customerId = cs.customerId AND apt.userId = us.userId "
                + "WHERE us.userId = " + searchId;  
        
        populateTV(apptQuery);
    }

    @FXML
    private void onNewAppointmentClick(ActionEvent event) throws IOException {
        clearForm();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        String url = "/view/NewOrExistingBox.fxml";
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

                java.sql.Timestamp at = apptRs.getTimestamp("Time");
                LocalTime apptTime = at.toLocalDateTime().toLocalTime();
                java.sql.Date ad = apptRs.getDate("Date");
                LocalDate apptDate = ad.toLocalDate();
                String apptCustomer = apptRs.getString("customerName");

                Appointment newAppointment = new Appointment(apptDate, apptTime, apptCustomer);

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
