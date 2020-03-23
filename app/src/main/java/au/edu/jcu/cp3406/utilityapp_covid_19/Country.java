package au.edu.jcu.cp3406.utilityapp_covid_19;

public class Country {
    int cases;
    int deaths;
    int recovered;

    void Country () {
        cases = 0;
        deaths = 0;
        recovered = 0;
    }

    public void updateInfo(String country) {
        //Get updated numbers from worldometers.info/coronavirus
        cases = 5683;
        deaths = 281;
        recovered = 93;
    }

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }
}
