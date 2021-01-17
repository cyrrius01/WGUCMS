package controller;

import dao.DAOAppointment;
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
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
        
        
        
        
        Statement apptStatement = DBQuery.getStatement();
        String apptQuery = "SELECT CAST(apt.start AS DATE) AS 'Date', CAST(apt.start AS TIME) AS 'Time', cs.customerName"
                + " FROM U04jTC.appointment apt JOIN U04jTC.customer cs JOIN U04jTC.user us ON apt.customerId = cs.customerId AND apt.userId = us.userId ";                
               
        try {
            apptStatement.execute(apptQuery);
            ResultSet apptRs = apptStatement.getResultSet();

            while(apptRs.next()){
                
                java.sql.Timestamp at = apptRs.getTimestamp("Time");
                LocalTime apptTime = at.toLocalDateTime().toLocalTime();
                java.sql.Date ad = apptRs.getDate("Date");
                LocalDate apptDate = ad.toLocalDate();
                String apptCustomer = apptRs.getString("customerName");
                
                Appointment newAppointment = new Appointment(apptDate, apptTime, apptCustomer);
        
                Appointment.addAppointment(newAppointment);
                System.out.println("Added appointment");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // start population of TableView
        MainScreenTableView.setItems(Appointment.getAllAppointments());
        
        DateTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptDate"));
        TimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptTime"));
        CustomerTableColumn.setCellValueFactory(new PropertyValueFactory<>("apptCustomer"));
       
        
        
    }    

    @FXML
    private void OnMonthHyperlink(ActionEvent event) {
    }

    @FXML
    private void OnWeekHyperlink(ActionEvent event) {
    }

    @FXML
    private void OnAllHyperlink(ActionEvent event) {
    }

    @FXML
    private void onNewAppointmentClick(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/NewOrExistingBox.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void onManageCustomerClick(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerSearch.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private void OnReportsClick(ActionEvent event) throws IOException {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        
    }

    @FXML
    private void OnExitClick(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        
        System.out.println("Main Exit Clicked");
        System.exit(0);
    
        
    }
    
}
