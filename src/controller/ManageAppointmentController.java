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
        ComboBoxStartTime.getItems().addAll(LocalTime.parse("08:00"),
                LocalTime.parse("08:15"),
                LocalTime.parse("08:30"),
                LocalTime.parse("08:45"),
                LocalTime.parse("09:00"),
                LocalTime.parse("09:15"),
                LocalTime.parse("09:30"),
                LocalTime.parse("09:45"),
                LocalTime.parse("10:00"),
                LocalTime.parse("10:15"),
                LocalTime.parse("10:30"),
                LocalTime.parse("10:45"),
                LocalTime.parse("11:00"),
                LocalTime.parse("11:15"),
                LocalTime.parse("11:30"),
                LocalTime.parse("11:45"),
                LocalTime.parse("12:00"),
                LocalTime.parse("12:15"),
                LocalTime.parse("12:30"),
                LocalTime.parse("12:45"),
                LocalTime.parse("13:00"),
                LocalTime.parse("13:15"),
                LocalTime.parse("13:30"),
                LocalTime.parse("13:45"),
                LocalTime.parse("14:00"),
                LocalTime.parse("14:15"),
                LocalTime.parse("14:30"),
                LocalTime.parse("14:45"),
                LocalTime.parse("15:00"),
                LocalTime.parse("15:15"),
                LocalTime.parse("15:30"),
                LocalTime.parse("15:45"),
                LocalTime.parse("16:00"),
                LocalTime.parse("16:15"),
                LocalTime.parse("16:30"),
                LocalTime.parse("16:45"),
                LocalTime.parse("17:00"),
                LocalTime.parse("17:15"),
                LocalTime.parse("17:30"),
                LocalTime.parse("17:45"),
                LocalTime.parse("18:00"),
                LocalTime.parse("18:15"),
                LocalTime.parse("18:30"),
                LocalTime.parse("18:45"),
                LocalTime.parse("19:00"),
                LocalTime.parse("19:15"),
                LocalTime.parse("19:30"),
                LocalTime.parse("19:45"),
                LocalTime.parse("20:00"),
                LocalTime.parse("20:15"),
                LocalTime.parse("20:30"),
                LocalTime.parse("20:45"),
                LocalTime.parse("21:00"),
                LocalTime.parse("21:15"),
                LocalTime.parse("21:30"),
                LocalTime.parse("21:45"),
                LocalTime.parse("22:00")
        );
        // breakdown timestamp to local time
        LocalDateTime ldt = apptStart;
        LocalTime loct = apptStart.toLocalTime();
        System.out.println(ldt + " = ldt");
        LocalDate ld = apptStart.toLocalDate();

        int startTimeIndex = ComboBoxStartTime.getItems().indexOf(loct);
        ComboBoxStartTime.getSelectionModel().select(startTimeIndex);

        DatePickerStartDate.setValue(ld);


        ComboBoxEndTime.getItems().addAll(LocalTime.parse("08:00"),
                LocalTime.parse("08:15"),
                LocalTime.parse("08:30"),
                LocalTime.parse("08:45"),
                LocalTime.parse("09:00"),
                LocalTime.parse("09:15"),
                LocalTime.parse("09:30"),
                LocalTime.parse("09:45"),
                LocalTime.parse("10:00"),
                LocalTime.parse("10:15"),
                LocalTime.parse("10:30"),
                LocalTime.parse("10:45"),
                LocalTime.parse("11:00"),
                LocalTime.parse("11:15"),
                LocalTime.parse("11:30"),
                LocalTime.parse("11:45"),
                LocalTime.parse("12:00"),
                LocalTime.parse("12:15"),
                LocalTime.parse("12:30"),
                LocalTime.parse("12:45"),
                LocalTime.parse("13:00"),
                LocalTime.parse("13:15"),
                LocalTime.parse("13:30"),
                LocalTime.parse("13:45"),
                LocalTime.parse("14:00"),
                LocalTime.parse("14:15"),
                LocalTime.parse("14:30"),
                LocalTime.parse("14:45"),
                LocalTime.parse("15:00"),
                LocalTime.parse("15:15"),
                LocalTime.parse("15:30"),
                LocalTime.parse("15:45"),
                LocalTime.parse("16:00"),
                LocalTime.parse("16:15"),
                LocalTime.parse("16:30"),
                LocalTime.parse("16:45"),
                LocalTime.parse("17:00"),
                LocalTime.parse("17:15"),
                LocalTime.parse("17:30"),
                LocalTime.parse("17:45"),
                LocalTime.parse("18:00"),
                LocalTime.parse("18:15"),
                LocalTime.parse("18:30"),
                LocalTime.parse("18:45"),
                LocalTime.parse("19:00"),
                LocalTime.parse("19:15"),
                LocalTime.parse("19:30"),
                LocalTime.parse("19:45"),
                LocalTime.parse("20:00"),
                LocalTime.parse("20:15"),
                LocalTime.parse("20:30"),
                LocalTime.parse("20:45"),
                LocalTime.parse("21:00"),
                LocalTime.parse("21:15"),
                LocalTime.parse("21:30"),
                LocalTime.parse("21:45"),
                LocalTime.parse("22:00"));

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

    public void onComboBoxEndTime(ActionEvent actionEvent) {
    }

    public void onButtonSave(ActionEvent actionEvent) {

        if(!TextFieldTitle.getText().equals(apptTitle) || !TextFieldDescription.getText().equals(apptDescription) || !TextFieldLocation.getText().equals(apptLocation) ||
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
            System.out.println(newStime + " is newStime");
            LocalDate newEdate = DatePickerEndDate.getValue();
            LocalTime newEtime = (LocalTime) ComboBoxEndTime.getValue();
            String newCustomerID = TextFieldCustomerID.getText();
            String newUserID = TextFieldUserID.getText();
            String newApptID = TextFieldAppointmentID.getText();

            LocalDateTime start = LocalDateTime.of(newSdate, newStime);
            //ZonedDateTime startzdt = start.atZone(ZoneId.systemDefault());
            //ZonedDateTime startTzdt = startzdt.withZoneSameInstant(ZoneId.of("UTC"));
            //LocalDateTime startLdt = startTzdt.toLocalDateTime();
            Timestamp startTime = Timestamp.valueOf(start);


            LocalDateTime end = LocalDateTime.of(newEdate, newEtime);
            //ZonedDateTime endzdt = end.atZone(ZoneId.systemDefault());
            //ZonedDateTime endTzdt = endzdt.withZoneSameInstant(ZoneId.of("UTC"));
            //LocalDateTime endLdt = endTzdt.toLocalDateTime();
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
