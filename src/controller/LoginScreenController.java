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
import java.time.LocalDateTime;
import java.util.Arrays;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
        LabelUsername.setText(languageRB.getString("Username"));
        LabelPassword.setText(languageRB.getString("Password"));
        LoginButton.setText(languageRB.getString("Login"));
    }
    
    public void onLogin(ActionEvent event) throws SQLException, IOException {
        
         // check user locale since user has launched application
        ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
      
        // language code is lowercase ("fr"); country code is uppercase ("FR")
        // property file resource bundle naming convention: 
        //      ResourceBundleName_languageCode_optionalCountryCode.properties
        
        String uname = unameTextbox.getText();
        String pword = pwordTextbox.getText();
        String combined = uname+pword;
        
        if(uname.equals("") || pword.equals("")){
            Alert blankUnamePass = new Alert(AlertType.INFORMATION);
            blankUnamePass.setHeaderText(null);
            blankUnamePass.setContentText(languageRB.getString("no.blank"));
            blankUnamePass.showAndWait();
        }
            
            // Start DB Connection            
            Connection conn = dao.DBConnection.startConnection();
            DBQuery.setStatement(conn); // create statement object
            Statement statement = DBQuery.getStatement(); // get statement reference
            
            // Begin SQL query
            String queryStatement = "SELECT CONCAT(userName, password) as combined FROM U04jTC.user WHERE userName = '" + uname + "' AND password = '" + pword + "'";
            statement.execute(queryStatement);
            ResultSet rs = statement.getResultSet(); // getting results from the SQL query
            
            while(rs.next()) {
               
               queryResult = rs.getString("combined"); // "combined" is the column name of the resultset
            }
            
            if(queryResult != null && queryResult.equals(combined)) {
                
                // create log file
                String logText = uname + " login at " + LocalDateTime.now();
                File myFile = new File("logfile.txt");
                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File exists");
                }
                Path path = Paths.get("logfile.txt");
                Files.write(path, Arrays.asList(logText), StandardCharsets.UTF_8, Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
                
                
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

                Stage newStage = new Stage();
                newStage.setTitle(null);
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.show();
                 

            } else {
                
                Alert invalidUnamePass = new Alert(AlertType.INFORMATION);
                invalidUnamePass.setHeaderText(null);
                invalidUnamePass.setContentText(languageRB.getString("invalid"));
                invalidUnamePass.showAndWait();
            }

    }

}
