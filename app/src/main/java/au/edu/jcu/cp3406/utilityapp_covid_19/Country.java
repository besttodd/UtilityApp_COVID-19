package au.edu.jcu.cp3406.utilityapp_covid_19;

/*
This class sets up data for a certain country to extract data from the COVID-19 website.
This includes:
the URL for that particular country,
the countries flag image,
and a graph of current statistics for that particular country
 */

class Country {
    private String cases;
    private String deaths;
    private String recovered;
    private String url;
    private int img;
    private int graph;

    //DEFAULT
    Country() {
        cases = "";
        deaths = "";
        recovered = "";
        url = "https://www.worldometers.info/coronavirus/country/uk/";
        img = R.drawable.uk;
        graph = R.drawable.ukgraph;
    }

    void setInfo(String country) {
        switch (country) {
            case "United Kingdom":
                url = "https://www.worldometers.info/coronavirus/country/uk/";
                img = R.drawable.uk;
                graph = R.drawable.ukgraph;
                break;
            case "United States":
                url = "https://www.worldometers.info/coronavirus/country/us/";
                img = R.drawable.usa;
                graph = R.drawable.usgraph;
                break;
            case "Australia":
                url = "https://www.worldometers.info/coronavirus/country/australia/";
                img = R.drawable.aus;
                graph = R.drawable.ausgraph;
                break;
            case "Italy":
                url = "https://www.worldometers.info/coronavirus/country/italy/";
                img = R.drawable.italy;
                graph = R.drawable.italygraph;
                break;
            case "China":
                url = "https://www.worldometers.info/coronavirus/country/china/";
                img = R.drawable.china;
                graph = R.drawable.chinagraph;
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

    int getGraph() { return graph; }
}
