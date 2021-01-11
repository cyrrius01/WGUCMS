package dao;

import java.time.LocalDateTime;

/**
 *
 * @author Keith A Graham
 */
public class DAOStudent_tbl {
    private int StudentID;
    private String StudentName;
    private String Phone;
    private int Active;
    private LocalDateTime Enrollment;
    private LocalDateTime RecordEntryDate;
    private String RecordEntryDateS;

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }

    public LocalDateTime getEnrollment() {
        return Enrollment;
    }

    public void setEnrollment(LocalDateTime Enrollment) {
        this.Enrollment = Enrollment;
    }

    public LocalDateTime getRecordEntryDate() {
        return RecordEntryDate;
    }

    public void setRecordEntryDate(LocalDateTime RecordEntryDate) {
        this.RecordEntryDate = RecordEntryDate;
    }

    public String getRecordEntryDateS() {
        return RecordEntryDateS;
    }

    public void setRecordEntryDateS(String RecordEntryDateS) {
        this.RecordEntryDateS = RecordEntryDateS;
    }
    
    
}
