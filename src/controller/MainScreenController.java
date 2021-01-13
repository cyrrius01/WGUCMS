package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

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
    private TableView<?> MainScreenTableView;
    @FXML
    private TableColumn<?, ?> DateTableColumn;
    @FXML
    private TableColumn<?, ?> TimeTableColumn;
    @FXML
    private TableColumn<?, ?> CustomerTableColumn;
    
    private int minutes = 15;

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
        
        
        // This is where we will do the appointment check within 15 minutes of login
        
        // order of ops
        // do a SQL query to see what next appointment time is
        // compare the result set to the current time
        // if appointment time minus current time is less than 15 minutes
        // then pop alert box
        
        
            
            
        
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
