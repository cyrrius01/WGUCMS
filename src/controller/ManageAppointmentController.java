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
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

/**
 * This controller class allows a user to update or delete an appointment.
 */
public class ManageAppointmentController implements Initializable {
    @FXML
    private Hyperlink WeekHyperlink;
    @FXML
    private TextField TextFieldTitle;
    @FXML
    private TextField TextFieldDescription;
    @FXML
    private TextField TextFieldLocation;
    @FXML
    private ComboBox ComboBoxContact;
    @FXML
    private ComboBox ComboBoxAppointmentType;
    @FXML
    private DatePicker DatePickerStartDate;
    @FXML
    private ComboBox ComboBoxStartTime;
    @FXML
    private DatePicker DatePickerEndDate;
    @FXML
    private ComboBox ComboBoxEndTime;
    @FXML
    private TextField TextFieldCustomerID;
    @FXML
    private TextField TextFieldUserID;
    @FXML
    private TextField TextFieldAppointmentID;

    static int apptAppointment_ID;
    static String apptTitle;
    static String apptDescription;
    static String apptLocation;
    static String apptContact_Name;
    static String apptType;
    static LocalDateTime apptStart;
    static LocalDateTime apptEnd;
    static int apptCustomer_ID;
    static int apptUser_ID;



    public static ObservableList options = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> allTimes = FXCollections.observableArrayList();

    /**
     * This method takes a football passed from the Main Screen Controller and creates variables usable by the methods below
     *
     * @param appointment
     */
    public static void football(Appointment appointment){

        apptAppointment_ID = appointment.getApptAppointment_ID();
        apptTitle = appointment.getApptTitle();
        apptDescription = appointment.getApptDescription();
        apptLocation = appointment.getApptLocation();
        apptContact_Name = appointment.getApptContact_Name();
        apptType = appointment.getApptType();
        apptStart = appointment.getApptStart();
        apptEnd = appointment.getApptEnd();
        apptCustomer_ID = appointment.getApptCustomer_ID();
        apptUser_ID = appointment.getApptUser_ID();

    }

    /**
     * On initialize, all data fields are set to their respective values, operating hours are set,
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        DBQuery.contacts();

        TextFieldAppointmentID.setText(Integer.toString(apptAppointment_ID));
        TextFieldTitle.setText(apptTitle);
        TextFieldDescription.setText(apptDescription);
        TextFieldLocation.setText(apptLocation);

        // populate combo boxes and then set defaults

        ComboBoxContact.setItems(DBQuery.options);

        int contactIndex = ComboBoxContact.getItems().indexOf(apptContact_Name);
        ComboBoxContact.getSelectionModel().select(contactIndex);

        ComboBoxAppointmentType.getItems().addAll("Scrum", "Presentation", "Planning Session", "De-Briefing");
        int apptTypeIndex = ComboBoxAppointmentType.getItems().indexOf(apptType);
        ComboBoxAppointmentType.getSelectionModel().select(apptTypeIndex);

        //break out timestamps to time and date
        LocalDateTime easternStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0));
        ZonedDateTime easternZDT = easternStart.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localZDT = easternZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime startLocal = localZDT.toLocalTime();

        LocalDateTime easternEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
        ZonedDateTime easternZDTE = easternEnd.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime localZDTE = easternZDTE.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime endLocal = localZDTE.toLocalTime();

        LocalTime lts = startLocal;
        while (lts.isBefore(endLocal.plusSeconds(1))) {
            ComboBoxStartTime.getItems().add(lts);
            ComboBoxEndTime.getItems().add(lts);
            lts = lts.plusMinutes(15);
        }
        // breakdown timestamp to local time
        LocalDateTime ldt = apptStart;
        LocalTime loct = apptStart.toLocalTime();
        System.out.println(ldt + " = ldt");
        LocalDate ld = apptStart.toLocalDate();

        int startTimeIndex = ComboBoxStartTime.getItems().indexOf(loct);
        ComboBoxStartTime.getSelectionModel().select(startTimeIndex);

        DatePickerStartDate.setValue(ld);




        LocalDateTime enlt = apptEnd;
        LocalTime eloct = apptEnd.toLocalTime();
        LocalDate eld = apptEnd.toLocalDate();

        int endTimeIndex = ComboBoxEndTime.getItems().indexOf(eloct);
        ComboBoxEndTime.getSelectionModel().select(endTimeIndex);

        TextFieldCustomerID.setText(Integer.toString(apptCustomer_ID));
        TextFieldUserID.setText(Integer.toString(apptUser_ID));



        DatePickerEndDate.setValue(eld);

    }

    public void onComboBoxContact(ActionEvent actionEvent) {
    }

    public void onComboBoxAppointmentType(ActionEvent actionEvent) {
    }

    public void onDatePickerStartDate(ActionEvent actionEvent) {
    }

    public void onComboBoxStartTime(ActionEvent actionEvent) {
    }

    public void onDatePickerEndDate(ActionEvent actionEvent) {
    }

    /**
     * This method checks to see that the end time is after the start time
     *
     * @param actionEvent
     */
    public void onComboBoxEndTime(ActionEvent actionEvent) {

        LocalTime s = (LocalTime) ComboBoxStartTime.getValue();
        LocalTime e = (LocalTime) ComboBoxEndTime.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kk:mm");
        String stext = s.format(formatter);
        String etext = e.format(formatter);
        LocalTime sParsedTime = LocalTime.parse(stext);
        LocalTime eParsedTime = LocalTime.parse(etext);

        if (sParsedTime.isAfter(eParsedTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "End time must be after start time.");
            // the following lambda expression causes "alert" to display, looks for the OK button to be pressed,
            // and upon being pressed will cause the items in the End Time combo box to show
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> ComboBoxEndTime.show());
        }
    }

    /**
     * On save, the method checks for any altered values and if any changes are found, a new appointment object is created and saved to the database table
     *
     * @param actionEvent
     */
    public void onButtonSave(ActionEvent actionEvent) {

        LocalTime s = (LocalTime) ComboBoxStartTime.getValue();
        LocalTime e = (LocalTime) ComboBoxEndTime.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kk:mm");
        String stext = s.format(formatter);
        String etext = e.format(formatter);
        LocalTime sParsedTime = LocalTime.parse(stext);
        LocalTime eParsedTime = LocalTime.parse(etext);

        if (sParsedTime.isAfter(eParsedTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "End time must be after start time.");
            // the following lambda expression causes "alert" to display, looks for the OK button to be pressed,
            // and upon being pressed will cause the items in the End Time combo box to show
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> ComboBoxEndTime.show());
        } else {

            if (!TextFieldTitle.getText().equals(apptTitle) || !TextFieldDescription.getText().equals(apptDescription) || !TextFieldLocation.getText().equals(apptLocation) ||
                    !ComboBoxContact.getSelectionModel().getSelectedItem().equals(apptContact_Name) || !ComboBoxAppointmentType.getSelectionModel().getSelectedItem().equals(apptType) ||
                    !DatePickerStartDate.getValue().equals(apptStart.toLocalDate()) ||
                    !ComboBoxStartTime.getSelectionModel().getSelectedItem().equals(apptStart.toLocalTime()) ||
                    !DatePickerEndDate.getValue().equals(apptEnd.toLocalDate()) ||
                    !ComboBoxEndTime.getSelectionModel().getSelectedItem().equals(apptEnd.toLocalTime()) ||
                    !TextFieldCustomerID.getText().equals(apptCustomer_ID) || !TextFieldUserID.getText().equals(apptUser_ID)) {

                String newTitle = TextFieldTitle.getText();
                String newDescription = TextFieldDescription.getText();
                String newLocation = TextFieldLocation.getText();
                String newContact = (String) ComboBoxContact.getValue();
                String newType = (String) ComboBoxAppointmentType.getValue();
                LocalDate newSdate = DatePickerStartDate.getValue();
                LocalTime newStime = (LocalTime) ComboBoxStartTime.getValue();
                LocalDate newEdate = DatePickerEndDate.getValue();
                LocalTime newEtime = (LocalTime) ComboBoxEndTime.getValue();
                String newCustomerID = TextFieldCustomerID.getText();
                String newUserID = TextFieldUserID.getText();
                String newApptID = TextFieldAppointmentID.getText();

                LocalDateTime start = LocalDateTime.of(newSdate, newStime);
                Timestamp startTime = Timestamp.valueOf(start);
                LocalDateTime end = LocalDateTime.of(newEdate, newEtime);
                Timestamp endTime = Timestamp.valueOf(end);


                DBQuery.updateAppointment(newApptID, newTitle, newDescription, newLocation, newContact, newType, startTime, endTime, newCustomerID, newUserID);

                Alert blank = new Alert(Alert.AlertType.INFORMATION);
                blank.setHeaderText(null);
                blank.setContentText("Changes saved.");
                blank.showAndWait();

            } else {
                Alert blank = new Alert(Alert.AlertType.INFORMATION);
                blank.setHeaderText(null);
                blank.setContentText("No changes to save.");
                blank.showAndWait();
            }
        }
    }

    /**
     * On cancel, any changes made are ignored and the window closed returning the user to the main screen
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void onButtonCancel(ActionEvent event) throws IOException {

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));

        Stage newStage = new Stage();
        newStage.setTitle(null);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();

    }

    /**
     * ON delete, the user is asked to confirm deleting the appointment. If they confirm, an alert with the appointment ID and appointment type is shown on deletion
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onButtonDelete(ActionEvent actionEvent) throws IOException{

        Alert blank = new Alert(Alert.AlertType.CONFIRMATION, "Press OK to delete the appointment.");

        blank.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                DBQuery.deleteAppt(apptAppointment_ID);

                Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                newAlert.setHeaderText(null);
                newAlert.setContentText("Appointment ID " + apptAppointment_ID + " with Appointment Type " + apptType + " deleted successfully.");
                newAlert.showAndWait();

                ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage newStage = new Stage();
                newStage.setTitle(null);
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                newStage.show();
            }
        });
    }

}
