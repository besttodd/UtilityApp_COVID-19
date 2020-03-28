package au.edu.jcu.cp3406.utilityapp_covid_19;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

class Country {
    private String cases;
    private String deaths;
    private String recovered;
    private String country;
    private String url;
    private int img;

    Country() {
        /*cases = "";
        deaths = "";
        recovered = "";*/
        country = "Australia";
        url = "https://www.worldometers.info/coronavirus/country/australia/"; //DEFAULT
        img = R.drawable.aus;
        new getNewInfo().execute();
    }

    void setInfo(String country) {
        this.country = country;

        //Get updated numbers from worldometers.info/coronavirus
        //Updated 27/3
        switch (country) {
            case "United Kingdom":
                /*cases = 14579;
                deaths = 759;
                recovered = 135;*/
                url = "https://www.worldometers.info/coronavirus/country/uk/";
                img = R.drawable.uk;
                new getNewInfo().execute();
                break;
            case "United States":
                /*cases = 86548;
                deaths = 1321;
                recovered = 1889;*/
                url = "https://www.worldometers.info/coronavirus/country/us/";
                img = R.drawable.usa;
                new getNewInfo().execute();
                break;
            case "Australia":
                /*cases = "3180";
                deaths = "13";
                recovered = "170";*/
                url = "https://www.worldometers.info/coronavirus/country/australia/";
                img = R.drawable.aus;
                new getNewInfo().execute();
                break;
            case "Italy":
                /*cases = "80589";
                deaths = "8215";
                recovered = "10361";*/
                url = "https://www.worldometers.info/coronavirus/country/itay/";
                img = R.drawable.italy;
                new getNewInfo().execute();
                break;
            case "China":
                /*cases = "81340";
                deaths = "3292";
                recovered = "74588";*/
                url = "https://www.worldometers.info/coronavirus/country/china/";
                img = R.drawable.china;
                new getNewInfo().execute();
                break;
        }
    }

    String getCases() {
        return cases;
    }

    String getDeaths() {
        return deaths;
    }

    String getRecovered() {
        return recovered;
    }

    int getImage() {
        return img;
    }

    String getUrl() {
        return url;
    }

    void setCases(String cases) {
        this.cases = cases;
    }

    void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public class getNewInfo extends AsyncTask<Void,Void,Void> {
        String rawData;
        String relevant;
        String[] data;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(url).get();
                rawData = doc.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String start = country + " Coronavirus Cases: ";
            String end = "Active Cases";
            if (rawData.length() != 0) {
                relevant = rawData.substring(rawData.indexOf(start) + start.length() - 7);
                relevant = relevant.substring(0, relevant.indexOf(end));
                data = relevant.split(" ", 6);
                cases = data[1];
                deaths = data[3];
                recovered = data[5];
            }
            return null;
        }
    }
}
