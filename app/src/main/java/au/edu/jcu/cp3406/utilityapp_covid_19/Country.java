package au.edu.jcu.cp3406.utilityapp_covid_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class Country {
    int cases;
    int deaths;
    int recovered;
    String updatedData;
    String country;

    void Country () {
        cases = 0;
        deaths = 0;
        recovered = 0;
        updatedData = "None";
        country = "Not set";
    }

    public void setInfo(String country) {
        this.country = country;
        updatedData = "RAW data";

        //Get updated numbers from worldometers.info/coronavirus
        cases = 8807;
        deaths = 281;
        recovered = 93;
    }

    int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setUpdatedData(String newData) {
        this.updatedData = newData;
    }

    String getUpdatedData() {
        return updatedData;
    }

    void getNewData() {

    }
}
