package au.edu.jcu.cp3406.utilityapp_covid_19;

class Country {
    private int cases;
    private int deaths;
    private int recovered;
    private String updatedData;
    private String country;
    private int img;

    void Country () {
        cases = 0;
        deaths = 0;
        recovered = 0;
        updatedData = "None";
        country = "Not set";
        img = R.drawable.uk; //DEFAULT
    }

    void setInfo(String country) {
        this.country = country;
        updatedData = "RAW data";

        //Get updated numbers from worldometers.info/coronavirus
        //Updated 27/3
        switch (country) {
            case "United Kingdom":
                cases = 14579;
                deaths = 759;
                recovered = 135;
                img = R.drawable.uk;
                break;
            case "United States":
                cases = 86548;
                deaths = 1321;
                recovered = 1889;
                img = R.drawable.usa;
                break;
            case "Australia":
                cases = 3180;
                deaths = 13;
                recovered = 170;
                img = R.drawable.aus;
                break;
            case "Italy":
                cases = 80589;
                deaths = 8215;
                recovered = 10361;
                img = R.drawable.italy;
                break;
            case "China":
                cases = 81340;
                deaths = 3292;
                recovered = 74588;
                img = R.drawable.china;
                break;
        }
    }

    int getCases() {
        return cases;
    }

    int getDeaths() {
        return deaths;
    }

    int getRecovered() {
        return recovered;
    }

    String getUpdatedData() {
        return updatedData;
    }

    int getImage() {
        return img;
    }
}
