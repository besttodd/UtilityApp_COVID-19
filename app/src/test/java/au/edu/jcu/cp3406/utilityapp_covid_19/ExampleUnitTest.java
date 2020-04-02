package au.edu.jcu.cp3406.utilityapp_covid_19;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetData() {
        String rawData = "";
        String relevant;
        String[] data = null;

        try {
            Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/uk").get();
            rawData = doc.text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String start = "United Kingdom Coronavirus Cases: ";
        String end = "Active Cases";
        if (!rawData.equals("")) {
            relevant = rawData.substring(rawData.indexOf(start) + start.length() - 7);
            relevant = relevant.substring(0, relevant.indexOf(end));
            data = relevant.split(" ", 6);
        }
        assert data != null;
        System.out.println("Cases: " + data[1] + " Deaths: " + data[3] + " Recovered: " + data[5]);
    }

    @Test
    public void testConstructor() {
        Country testCountry = new Country();
        assertEquals("https://www.worldometers.info/coronavirus/country/uk/", testCountry.getUrl());
        assertEquals(R.drawable.uk, testCountry.getImage());
        assertEquals(R.drawable.ukgraph, testCountry.getGraph());
    }

    @Test
    public void testCountryClass() {
        Country testCountry = new Country();
        testCountry.setInfo("Australia");
        assertEquals("https://www.worldometers.info/coronavirus/country/australia/", testCountry.getUrl());
        assertEquals(R.drawable.aus, testCountry.getImage());
        assertEquals(R.drawable.ausgraph, testCountry.getGraph());
    }
}
