/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Appointment(LocalDate apptDate, LocalTime apptTime, String apptCustomer) {
        
        
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.apptCustomer = apptCustomer;
    }
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    
    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);        
    }
    
    
}
