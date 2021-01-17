package controller;

import dao.DBQuery;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.fxml.Initializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.TimeZone;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;




public class LoginScreenController implements Initializable {
    
    @FXML
    private Button LoginButton;
    @FXML
    private TextField unameTextbox;
    @FXML
    private PasswordField pwordTextbox;
    @FXML
    private Label LabelUsername;
    @FXML
    private Label LabelPassword;
    @FXML
    
    private String queryResult;
    private int flag;
    private int userId;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        LabelUsername.setText(languageRB.getString("Username"));
        LabelPassword.setText(languageRB.getString("Password"));
        LoginButton.setText(languageRB.getString("Login"));
    }
    
    public void genLogFile(String uname) throws IOException{
        String logText = uname + " login at " + LocalDateTime.now();
                File myFile = new File("logfile.txt");
                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File exists");
                }
                Path path = Paths.get("logfile.txt");
                Files.write(path, Arrays.asList(logText), StandardCharsets.UTF_8, 
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
    }
    
    public void apptCheck(int userId) throws SQLException {
        
        // this method checks for an appointment occurring within 15 minutes of login
        
        Statement apptStatement = DBQuery.getStatement();
        String apptQuery = "Select apt.start, cs.customerName from U04jTC.appointment apt "
                + "JOIN U04jTC.customer cs ON cs.customerId = apt.customerId WHERE "
                + "userId = " + userId + " AND start >= NOW() AND start < NOW() + interval 16 minute";
        apptStatement.execute(apptQuery);
        ResultSet apptRs = apptStatement.getResultSet();
        while(apptRs.next()) {
            Timestamp apptTime = apptRs.getTimestamp("start");
            ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
            Alert apptCheck = new Alert(AlertType.INFORMATION);
            apptCheck.setHeaderText(null);
            apptCheck.setContentText(languageRB.getString("apptSoon") + " " + apptTime);
           
            apptCheck.showAndWait();
           
           
        }    
            
    }
    
    
    public void onLogin(ActionEvent event) throws SQLException, IOException {
        
         // check user locale since user has launched application
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
      
        /*  language code is lowercase ("fr"); country code is uppercase ("FR")
             property file resource bundle naming convention: 
              ResourceBundleName_languageCode_optionalCountryCode.properties */
        
        
        String uname = unameTextbox.getText();
        String pword = pwordTextbox.getText();
        String combined = uname+pword;
        
        if(uname.equals("") || pword.equals("")){
            Alert blankUnamePass = new Alert(AlertType.INFORMATION);
            blankUnamePass.setHeaderText(null);
            blankUnamePass.setContentText(languageRB.getString("no.blank"));
            blankUnamePass.showAndWait();
            
        }   else{
            flag=1; // turn this flag on (1) to prevent invalid message from triggering when blank uname or pass is entered
        }
            
            // Start DB Connection            
            Connection conn = dao.DBConnection.startConnection();
            DBQuery.setStatement(conn); // create statement object
            Statement statement = DBQuery.getStatement(); // get statement reference
            

            // Begin SQL query
            String queryStatement = "SELECT userId, userName, password FROM U04jTC.user WHERE userName = '"
                    + uname + "' AND password = '" + pword + "'";
            statement.execute(queryStatement);
            ResultSet rs = statement.getResultSet(); // getting results from the SQL query
            
            while(rs.next()) {
               
               queryResult = rs.getString("userName") + rs.getString("password"); //combines username and pass (testtest)
               userId = rs.getInt("userId");
            
            }
            
            if(queryResult != null && queryResult.equals(combined)) {
                
                // valid login, create log file
                genLogFile(uname);
                
                // call to appointment check within 15 minutes
                apptCheck(userId);
                
                MainScreenController.receiver(userId);
                
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

                Stage newStage = new Stage();
                newStage.setTitle(null);
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.show();
                 

            } else if(flag==1) {
                
                Alert invalidUnamePass = new Alert(AlertType.INFORMATION);
                invalidUnamePass.setHeaderText(null);
                invalidUnamePass.setContentText(languageRB.getString("invalid"));
                invalidUnamePass.showAndWait();
            }

    }

}
