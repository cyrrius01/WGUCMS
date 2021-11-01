package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *The appointment class is used to create and update appointments.
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

    /**
     * Creates an appointment object
     * @param apptAppointment_ID    Appointment ID
     * @param apptTitle             Appointment Title
     * @param apptDescription       Appointment Description
     * @param apptLocation          Appointment Location
     * @param apptContact_Name      Contact Name
     * @param apptType              Appointment Type
     * @param apptStart             Appointment Start Date and Time
     * @param apptEnd               Appointment End Date and Time
     * @param apptCustomer_ID       Customer ID
     * @param apptUser_ID           User ID
     */
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

    /**
     * Creates an appointment object
     * @param apptAppointment_ID    Appointment ID
     * @param apptTitle             Appointment Title
     * @param apptType              Appointment Type
     * @param apptDescription       Appointment Description
     * @param apptStart             Appointment Start Date and Time
     * @param apptEnd               Appointment End Date and Time
     * @param apptCustomer_ID       Customer ID
     */
    public Appointment(int apptAppointment_ID, String apptTitle, String apptType, String apptDescription, LocalDateTime apptStart, LocalDateTime apptEnd, int apptCustomer_ID) {
        this.apptAppointment_ID = apptAppointment_ID;
        this.apptTitle = apptTitle;
        this.apptType = apptType;
        this.apptDescription = apptDescription;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.apptCustomer_ID = apptCustomer_ID;
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


    
    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);        
    }
    

    
    
}
