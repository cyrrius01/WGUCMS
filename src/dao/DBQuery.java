package dao;

import com.mysql.cj.protocol.Resultset;
import controller.CustomerController;
import controller.NewCustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * DBQuery houses most queries and their execution within the application
 *
 */
public class DBQuery {
    public static ObservableList options = FXCollections.observableArrayList();
    public static ObservableList stateOptions = FXCollections.observableArrayList();

    public static String selected = NewCustomerController.selected;

    private static Statement statement; // Statement reference
    
    // this is where we create the statement object
    public static void setStatement(Connection connection) throws SQLException {
    
        statement = connection.createStatement();

    }

    /**
     * this is where we return the statement object
     * @return  returns the statement object
     */

    public static Statement getStatement() {
        
        return statement;
        
    }
    private static int searchId;

    /**
     * Update Customer is called when a customer change is made.
     * @param id            The id of the customer
     * @param newName       The name of the customer
     * @param newAddress    The address of the customer
     * @param newPostal     The zip code of the customer
     * @param newPhone      The phone number of the customer
     * @param newState      The state of the customer
     * @param newCountry    The country of the customer
     */
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

    /**
     * Delete Customer is called when a customer is to be deleted.
     * @param id    The id of the customer
     */
    public static void deleteCustomer(int id) {

        // to do delete appointment first using customer_id then delete customer
        try {
            String query = "DELETE FROM appointments WHERE Customer_ID = '" + id + "';";
            statement.execute(query);

            String newQuery = "DELETE FROM customers WHERE Customer_ID = '" + id + "';";
            statement.execute(newQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when an appointment is getting deleted.
     * @param apptAppointment_id    The id of the appointment
     */
    public static void deleteAppt(int apptAppointment_id) {

        try {

            String query = "DELETE FROM appointments WHERE Appointment_ID = '" + apptAppointment_id + "';";
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns the appointments for the selected contact ID. Used on the Reports screen
     * @param i     The contact ID
     * @return      Returns the result set
     */
    public static ResultSet contactAppointments(int i) {
        ResultSet rs = null;
        String query = "select apt.Appointment_ID, apt.Title, apt.Type, apt.Description, apt.Start, apt.End, apt.Customer_ID from appointments apt join contacts ct on apt.Contact_ID = ct.Contact_ID where apt.Contact_ID =" + i;
        try {
            statement.execute(query);
            rs = statement.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * This is used to get the count of the type of appointment by month on the report screen
     * @param type      The type of appointment
     * @param month     The month the appointment is in
     * @return          Returns the count of appointment types by month
     */
    public static int getMonthTypeCount(String type, String month) {
        int i = 0;
        String query = "select count(type) as count from appointments where type = '" + type + "' and monthname(Start) = '" + month + "' and monthName(End) = '" + month + "'";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            rs.next();
            i = rs.getInt("count");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Used for login screen to set the searchId
     * @param userId    the user Id of the user logging in
     */
    public static void receiver(int userId){
        searchId = userId;
    }

    /**
     * String that gets all appointments
     * @return  Returns the query string
     */
    public static String getAllAppointments(){

        String apptQuery = "SELECT apt.Appointment_ID, apt.Title, apt.Description, apt.Location, ctc.Contact_Name, apt.Type, apt.Start, apt.End, " +
                "apt.Customer_ID, apt.User_ID FROM appointments apt JOIN contacts ctc ON apt.Contact_ID=ctc.Contact_ID JOIN users us ON us.User_ID=apt.User_ID " +
                "WHERE us.User_ID = " + searchId;

        return apptQuery;
    }

    /**
     * String that gets the appointments for the month
     * @return  Returns the query string
     */
    public static String getMonthAppointments() {

        String apptQuery = "SELECT apt.Appointment_ID, apt.Title, apt.Description, apt.Location, ctc.Contact_Name, apt.Type, apt.Start, apt.End, " +
                "apt.Customer_ID, apt.User_ID FROM appointments apt JOIN contacts ctc ON apt.Contact_ID=ctc.Contact_ID JOIN users us ON us.User_ID=apt.User_ID " +
                "WHERE us.User_ID = " + searchId + " AND apt.Start >= DATE_SUB(LAST_DAY(NOW()),INTERVAL DAY(LAST_DAY(NOW()))-1 DAY) AND apt.Start <= LAST_DAY(now())";
        return apptQuery;

    }

    /**
     * String that gets appointments for the week
     * @return  Returns the query string
     */
    public static String getWeekAppointments() {

        String apptQuery = "SELECT apt.Appointment_ID, apt.Title, apt.Description, apt.Location, ctc.Contact_Name, apt.Type, apt.Start, apt.End, " +
                "apt.Customer_ID, apt.User_ID FROM appointments apt JOIN contacts ctc ON apt.Contact_ID=ctc.Contact_ID JOIN users us ON us.User_ID=apt.User_ID " +
                "WHERE us.User_ID = " + searchId + " AND apt.Start >= now() AND apt.Start <= DATE(NOW() + INTERVAL(7-DAYOFWEEK(NOW())) DAY)";
        return apptQuery;

    }

    /**
     * Checks for appointment within 15 minutes of login and provides the appropriate message
     * @param userId    The userId of the user logging in
     * @throws SQLException
     */
    public static void apptCheck(int userId) throws SQLException {
        try {
            Statement apptStatement = DBQuery.getStatement();
            String apptQuery = "Select apt.Start, cs.Customer_Name, apt.Appointment_ID from appointments apt "
                    + "JOIN customers cs ON cs.Customer_ID = apt.Customer_ID WHERE "
                    + "User_ID = " + userId + " AND Start >= NOW() AND Start < NOW() + interval 16 minute";
            apptStatement.execute(apptQuery);
            ResultSet apptRs = apptStatement.getResultSet();

            if(apptRs.next()) {
                Timestamp apptTime = apptRs.getTimestamp("Start");
                int apptID = apptRs.getInt("Appointment_ID");
                ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
                Alert apptCheck = new Alert(Alert.AlertType.INFORMATION);
                apptCheck.setHeaderText(null);
                apptCheck.setContentText(languageRB.getString("apptSoon") + apptTime + " " + (languageRB.getString("apptID") + ":" + apptID));

                apptCheck.showAndWait();
            } else if(!apptRs.isBeforeFirst()) {
                ResourceBundle languageRB = ResourceBundle.getBundle("wgucms/RB", Locale.getDefault());
                Alert apptCheck = new Alert(Alert.AlertType.INFORMATION);
                apptCheck.setHeaderText(null);
                apptCheck.setContentText(languageRB.getString("noAppt"));
                apptCheck.showAndWait();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects the userID, UserName, and Password for the given uname and pword and returns the resultset
     * @param uname USername of the user logging in
     * @param pword Password of the user logging in
     * @return  Returns the resultset
     * @throws SQLException
     * @throws IOException
     */
    public static ResultSet login(String uname, String pword) throws SQLException, IOException {
        ResultSet rs = null;
        try {
            String queryStatement = "SELECT User_ID, User_Name, Password FROM users WHERE User_Name = '"
                    + uname + "' AND Password = '" + pword + "'";

            statement.execute(queryStatement);
            rs = statement.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    /**
     * Gets available countries to populate country combo boxes
     * @return  Returns the ObservableList options
     */
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

    /**
     * Takes the selected country and gets the states associated with it
     * @return Returns the ObservableList stateOptions
     */
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

    /**
     * Takes the selected country and gets the states associated with it
     * @return  Returns the ObservableList stateOptions
     */
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

    /**
     * Query to find the division ID of a given division name, or state id of a given state name
     * @param divisionName  Also known as state or province name
     * @return  Returns the Division ID of the state or province
     */
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

    /**
     * Inserts a customer into the Customers table
     * @param sendName          Name of the customer
     * @param sendPhone         Phone number of the customer
     * @param sendAddress       Address of the customer
     * @param sendCountry       Country of the customer
     * @param sendState         State of the customer
     * @param sendPostalCode    Postal code of the customer
     * @param sendDivisionID    DIvision ID of the customer
     */
    public static void newCustomerSave(String sendName, String sendPhone, String sendAddress, String sendCountry, String sendState, String sendPostalCode, int sendDivisionID) {

        String query = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                "VALUES ('" + sendName + "', '" + sendAddress + "', '" + sendPostalCode + "', '" + sendPhone + "', '" + sendDivisionID + "')";
        try {
            statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Queries all Contact Names from the table contacts
     * @return Returns the ObservableList options
     */
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

    /**
     * Prepared statement to insert an appointment into the appointments table
     * @param apptObject    The appointment object containing the variables necessary
     */
    public static void apptInsert(DAOAppointments apptObject) {
        try {

            String query = "INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, apptObject.getTitle());
            ps.setString(2, apptObject.getDescription());
            ps.setString(3, apptObject.getLocation());
            ps.setString(4, apptObject.getType());
            ps.setTimestamp(5, apptObject.getStart());
            ps.setTimestamp(6, apptObject.getEnd());
            ps.setInt(7, apptObject.getCustomerId());
            ps.setInt(8, apptObject.getUserId());
            ps.setInt(9, apptObject.getContactId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int apptId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * First selects the contact ID that matches the contact name given, then updates the appointments table with the new information
     * @param newApptID         Appointment ID of the appointment
     * @param newTitle          Title of the appointment
     * @param newDescription    Description of the appointment
     * @param newLocation       Location of the appointment
     * @param newContact        Contact for the appointment
     * @param newType           Type of appointment
     * @param start             Start date and time of appointment
     * @param end               End date and time of appointment
     * @param newCustomerID     Customer's ID
     * @param newUserID         User's ID
     */
    public static void updateAppointment(String newApptID,String newTitle,String newDescription,String newLocation,String newContact,String newType,
                                         Timestamp start,Timestamp end,String newCustomerID,String newUserID) {
            String contactID = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + newContact + "';";

            String query = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, " +
                    "Contact_ID = ? WHERE Appointment_ID = ?";

            try {
                statement.execute(contactID);
                ResultSet rs = statement.getResultSet();
                rs.next();
                int contactId = rs.getInt("Contact_ID");


                PreparedStatement ps = DBConnection.getConnection().prepareStatement(query);
                ps.setString(1, newTitle);
                ps.setString(2, newDescription);
                ps.setString(3, newLocation);
                ps.setString(4, newType);
                ps.setTimestamp(5, start);
                ps.setTimestamp(6, end);
                ps.setInt(7, Integer.parseInt(String.valueOf(newCustomerID)));
                ps.setInt(8, Integer.parseInt(String.valueOf(newUserID)));
                ps.setInt(9, contactId);
                ps.setInt(10, Integer.parseInt(String.valueOf(newApptID)));

                ps.execute();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * Looks up the contact ID for the given contact's name
     * @param contactNameField  The name of the contact
     * @return  Returns the contact ID
     */
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

    /**
     * This query looks up the number of active users
     * @return  Returns the count of active users
     */
    public static int activeUsers() {
        int count = 0;
        try {
            String query = "SELECT Count(*) as Count FROM client_schedule.users;";
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            rs.next();
            count = rs.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Query string to get all customers
     * @return Returns the query string
     */
    public static String getAllCustomers(){
        options.clear();

        String apptQuery = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, first_level_divisions.Division, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";

        return apptQuery;
    }

    /**
     * This query looks for an existing appointment during the given timeframes
     * @param customerID    The customer's ID
     * @param start         The start time and date
     * @param end           The end time and date
     * @return  Returns a true or false depending if an appointment is found.
     */
    public static boolean existingApptSearch(int customerID, Timestamp start, Timestamp end) {
        options.clear();
        int apptID = 0;
        boolean result = false;
        ResultSet rs = null;
        String apptQuery = "SELECT Appointment_ID FROM appointments WHERE (Start between '" + start + "' and '" + end + "') OR (End between '" + start + "' and '" + end + "') AND Customer_ID = " + customerID + ";";
        try {
            statement.execute(apptQuery);
            rs = statement.getResultSet();

            if(rs.next()) {
                result = true;
                System.out.println("appointment found");
            } else {
                result = false;
                System.out.println("no appointments found");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
