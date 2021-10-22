package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Keith A Graham
 */
public class DAOCustomers {
    private int customerId;
    private String customerName;
    private int customerAddress;
    private int postalCode;
    private int phone;
    private int divisionId;

    public ObservableList<DAOCustomers> allCustomers = FXCollections.observableArrayList();

    public DAOCustomers(int customerId, String customerName, int customerAddress, int postalCode, int phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(int customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
