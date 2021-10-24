package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Keith A Graham
 */
public class Appointment {

    int apptAppointment_ID;
    String apptTitle;
    String apptDescription;
    String apptLocation;
    String apptContact_Name;
    String apptType;
    LocalDateTime apptStart;
    LocalDateTime apptEnd;
    int apptCustomer_ID;
    int apptUser_ID;
    
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<LocalTime> allTimes = FXCollections.observableArrayList();
    
    public Appointment(int apptAppointment_ID, String apptTitle, String apptDescription, String apptLocation, String apptContact_Name, String apptType,
                       LocalDateTime apptStart, LocalDateTime apptEnd, int apptCustomer_ID, int apptUser_ID) {

        this.apptAppointment_ID = apptAppointment_ID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptContact_Name = apptContact_Name;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptCustomer_ID = apptCustomer_ID;
        this.apptUser_ID = apptUser_ID;
    }
    

    public void setApptAppointment_ID(int apptAppointment_ID) {
        this.apptAppointment_ID = apptAppointment_ID;
    }
    public int getApptAppointment_ID() {
        return apptAppointment_ID;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public String getApptContact_Name() {
        return apptContact_Name;
    }

    public void setApptContact_Name(String apptContact_Name) {
        this.apptContact_Name = apptContact_Name;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public LocalDateTime getApptStart() {
        return apptStart;
    }

    public void setApptStart(LocalDateTime apptStart) {
        this.apptStart = apptStart;
    }

    public LocalDateTime getApptEnd() {
        return apptEnd;
    }

    public void setApptEnd(LocalDateTime apptEnd) {
        this.apptEnd = apptEnd;
    }

    public int getApptCustomer_ID() {
        return apptCustomer_ID;
    }

    public void setApptCustomer_ID(int apptCustomer_ID) {
        this.apptCustomer_ID = apptCustomer_ID;
    }

    public int getApptUser_ID() {
        return apptUser_ID;
    }

    public void setApptUser_ID(int apptUser_ID) {
        this.apptUser_ID = apptUser_ID;
    }

    public int getApptCustomer() {
        return apptAppointment_ID;
    }
    
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    public static void clearAppointments() {
        allAppointments.clear();
    }


    public static ObservableList<LocalTime> getTime() {
        return allTimes;
    }
    public static void setTime(LocalTime time) {
        allTimes.addAll(LocalTime.parse("08:00"),
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
    }
    
    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);        
    }
    

    
    
}
