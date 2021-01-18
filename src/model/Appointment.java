package model;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Keith A Graham
 */
public class Appointment {
    
    private LocalDate apptDate;
    private LocalTime apptTime;
    private String apptCustomer;
    
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
    
    public Appointment(LocalDate apptDate, LocalTime apptTime, String apptCustomer) {
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.apptCustomer = apptCustomer;
    }
    
    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }
    
    public LocalDate getApptDate() {
        return apptDate;
    }
    
    public void setApptTime(LocalTime apptTime) {
        this.apptTime = apptTime;
    }
    
    public LocalTime getApptTime() {
        return apptTime;
    }
    
    public void setApptCustomer(String apptCustomer) {
        this.apptCustomer = apptCustomer;
    }
    
    public String getApptCustomer() {
        return apptCustomer;
    }
    
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    public static ObservableList<Appointment> getMonthAppointments() {
        return monthAppointments;
    }
    
    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);        
    }
    
    public static void addMonthAppointment(Appointment newMonthAppointment) {
        monthAppointments.add(newMonthAppointment);
    }
    
    
}
