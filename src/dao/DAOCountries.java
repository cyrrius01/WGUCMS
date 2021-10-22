package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

/**
 *
 * @author Keith A Graham
 */
public class DAOCountries {
    private int Country_ID;
    private String Country;

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public int getCountryId() {
        return Country_ID;
    }

    public void setCountryId(int Country_ID) {
        this.Country_ID = Country_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }


    
}
