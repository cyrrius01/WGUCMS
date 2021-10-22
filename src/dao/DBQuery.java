package dao;

import controller.CustomerController;
import controller.NewCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;


public class DBQuery {
    public static ObservableList options = FXCollections.observableArrayList();
    public static ObservableList stateOptions = FXCollections.observableArrayList();

    public static String selected = NewCustomerController.selected;

    private static Statement statement; // Statement reference
    
    // this is where we create the statement object
    public static void setStatement(Connection connection) throws SQLException {
    
        statement = connection.createStatement();

    }
    
    // this is where we return the statement object
    public static Statement getStatement() {
        
        return statement;
        
    }
    private static int searchId;

    public static void updateCustomer(int id, String newName, String newAddress, String newPostal, String newPhone, String newState, String newCountry) {

        String query = "UPDATE customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON " +
                "first_level_divisions.Country_ID = countries.Country_ID SET Customer_Name = '" + newName + "', Address = '" + newAddress + "', " +
                "Postal_Code = '"+ newPostal + "', Phone = '" + newPhone + "', Division = '" + newState + "', Country = '" + newCountry + "'WHERE Customer_ID " +
                "= " + id + ";";
        try {
            statement.execute(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer() {

        // to do delete appointment first using customer_id then delete customer

    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }
    public int getSearchId() {
        return this.searchId;
    }

    public static void receiver(int userId){
        searchId = userId;
    }
    public static String getAllAppointments(){

        String apptQuery = "SELECT CAST(apt.Start AS DATE) AS 'Date', CAST(apt.Start AS TIME) AS 'Time', cs.Customer_Name"
                + " FROM appointments apt JOIN customers cs JOIN users us ON apt.Customer_ID = cs.Customer_ID AND apt.User_ID = us.User_ID "
                + "WHERE us.User_ID = " + searchId;

        return apptQuery;
    }

    public static String getMonthAppointments() {

        String apptQuery = "SELECT CAST(apt.Start AS DATE) AS 'Date', CAST(apt.Start AS TIME) AS 'Time', cs.Customer_Name"
                + " FROM appointments apt JOIN customers cs JOIN users us ON apt.Customer_ID = cs.Customer_ID AND apt.User_ID = us.User_ID "
                + "WHERE us.User_ID = " + searchId + " AND apt.Start >= now() AND apt.Start <= LAST_DAY(now())";
        return apptQuery;
    }

    public static String getWeekAppointments() {

        String apptQuery = "SELECT CAST(apt.Start AS DATE) AS 'Date', CAST(apt.Start AS TIME) AS 'Time', cs.Customer_Name"
                + " FROM appointments apt JOIN customers cs JOIN users us ON apt.Customer_ID = cs.Customer_ID AND apt.User_ID = us.User_ID "
                + "WHERE us.User_ID = " + searchId + " AND apt.Start >= now() AND apt.Start <= DATE(NOW() + INTERVAL(7-DAYOFWEEK(NOW())) DAY)";
        return apptQuery;

    }

    public static void apptCheck(int userId) throws SQLException {
        Statement apptStatement = DBQuery.getStatement();
        String apptQuery = "Select apt.Start, cs.Customer_Name from appointments apt "
                + "JOIN customers cs ON cs.Customer_ID = apt.Customer_ID WHERE "
                + "User_ID = " + userId + " AND Start >= NOW() AND Start < NOW() + interval 16 minute";
        apptStatement.execute(apptQuery);
        ResultSet apptRs = apptStatement.getResultSet();

        while(apptRs.next()) {
            Timestamp apptTime = apptRs.getTimestamp("Start");

            ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
            Alert apptCheck = new Alert(Alert.AlertType.INFORMATION);
            apptCheck.setHeaderText(null);
            apptCheck.setContentText(languageRB.getString("apptSoon"));

            apptCheck.showAndWait();
        }
    }

    public static ResultSet login(String uname, String pword) throws SQLException, IOException {
        String queryStatement = "SELECT User_ID, User_Name, Password FROM users WHERE User_Name = '"
                + uname + "' AND Password = '" + pword + "'";
        statement.execute(queryStatement);
        ResultSet rs = statement.getResultSet();
        return rs;

    }

    public static ObservableList fillComboBox() {
        options.clear();
        String query = "SELECT Country FROM countries";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {

                options.add(rs.getString("Country"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    public static ObservableList NewfillStateComboBox() {
        stateOptions.clear();
        String query = "SELECT Division FROM first_level_divisions fld JOIN countries cs ON fld.Country_ID = " +
                "cs.Country_ID WHERE Country = '" + CustomerController.selected + "'";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {
                stateOptions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stateOptions;
    }

    public static ObservableList fillStateComboBox() {
        stateOptions.clear();
        String query = "SELECT Division FROM first_level_divisions fld JOIN countries cs ON fld.Country_ID = " +
                "cs.Country_ID WHERE Country = '" + NewCustomerController.selected + "'";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {
                stateOptions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stateOptions;
    }

    public static int findDivisionID(String divisionName) {
        String query = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + NewCustomerController.sendState +"'";
        int divisionID = 0;
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                divisionID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisionID;
    }

    public static void newCustomerSave(String sendName, String sendPhone, String sendAddress, String sendCountry, String sendState, String sendPostalCode, int sendDivisionID) {

        String query = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                "VALUES ('" + sendName + "', '" + sendAddress + "', '" + sendPostalCode + "', '" + sendPhone + "', '" + sendDivisionID + "')";
        try {
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }


    public static ObservableList contacts() {
        options.clear();
        String query = "SELECT Contact_Name FROM contacts";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                options.add(rs.getString("Contact_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return options;
    }

    public static void apptInsert(DAOAppointments apptObject) {

        String query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES ('"+apptObject.getTitle()+"', '"+apptObject.getDescription()+"', '"+ apptObject.getLocation()+"', '"+apptObject.getType()+"', '"+apptObject.getStart()+"', '"+apptObject.getEnd()+"', '"+apptObject.getCustomerId()+"', '"+apptObject.getUserId()+"', '"+apptObject.getContactId()+"')";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int contactIDLookup(String contactNameField) {
        int contactId = 0;
        String query = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + contactNameField + "'";

        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                contactId = rs.getInt("Contact_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactId;
    }



    public static String getAllCustomers(){
        options.clear();
        String apptQuery = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, first_level_divisions.Division, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";

        return apptQuery;
    }

}
