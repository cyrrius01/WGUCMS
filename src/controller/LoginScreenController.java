package controller;

import dao.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * This controls the login form a user first sees on execution of the program
 */
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
    private TextField localeTextbox;
    @FXML
    private Label LabelLocale;
    @FXML
    
    private String queryResult;
    private int flag;
    private int userId;


    /**
     * Initial sets all labels and buttons to be translatable using the resource bundle indicated
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        LabelUsername.setText(languageRB.getString("Username"));
        LabelPassword.setText(languageRB.getString("Password"));
        LabelLocale.setText(languageRB.getString("Locale"));

        ZoneId z = ZoneId.systemDefault();
        System.out.println(z.getDisplayName(TextStyle.FULL, Locale.ROOT));
        localeTextbox.setText(z.getDisplayName(TextStyle.FULL, Locale.ROOT)); // set locale ID to user's locale

        LoginButton.setText(languageRB.getString("Login"));
    }

    /**
     * This will create a log file if none exists logging user access and time. If the file exists, the file is updated with the same.
     *
     * @param uname
     * @throws IOException
     */
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


    /**
     * On login, the username and password are checked for validity and if not the appropriate error message displays in either english or french depending on locale
     * settings. A query runs to determine if the username and password exists in the database table and if so, the user is directed to the main screen.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
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
            Connection connection = dao.DBConnection.startConnection();
            DBQuery.setStatement(connection); // create statement object
            Statement statement = DBQuery.getStatement(); // get statement reference
            

            // Begin SQL query


            ResultSet rs = DBQuery.login(uname, pword);
            
            while(rs.next()) {
               
               queryResult = rs.getString("User_Name") + rs.getString("Password"); //combines username and pass (testtest)
               userId = rs.getInt("User_ID");
            
            }
            
            if(queryResult != null && queryResult.equals(combined)) {
                
                // valid login, create log file
                genLogFile(uname);
                
                // call to appointment check within 15 minutes
                DBQuery.apptCheck(userId);
                
                DBQuery.receiver(userId);
                
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
