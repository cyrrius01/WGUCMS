/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Keith A Graham
 */
public class Country {
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private int Country_ID;
    private String Country;

    public Country(int country_ID, String country) {
        Country_ID = country_ID;
        Country = country;
    }

    public Country(String country) {
        Country = country;
    }

    public Country(int country_ID) {
        Country_ID = country_ID;
    }


    public static ObservableList<Country> getAllCountries() {

        return allCountries;
    }

    public static void addCountry(Country newCountry) {
        allCountries.add(newCountry);
    }


}
