package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private static ObservableList<LocalTime> allTimes = FXCollections.observableArrayList();
    
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
    
    public static void addMonthAppointment(Appointment newMonthAppointment) {
        monthAppointments.add(newMonthAppointment);
    }
    
    
}
