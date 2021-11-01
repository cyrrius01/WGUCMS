/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private String Division;
    private String Country;

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * Creates a customer object
     * @param Customer_ID       Customer ID
     * @param Customer_Name     Customer Name
     * @param Address           Customer Address
     * @param Postal_Code       Customer Postal Code
     * @param Phone             Customer PHone Number
     * @param Division          Customer State or Province
     * @param Country           Customer Country
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, String Division, String Country) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division = Division;
        this.Country = Country;
    }

    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setPostal_Code(String Postal_Code) {
        this.Postal_Code = Postal_Code;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public String getDivision() {
        return Division;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCountry() {
        return Country;
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

}
