package au.edu.jcu.cp3406.utilityapp_covid_19;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

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
        URL url = null;
        try {
            url = new URL("https://www.worldometers.info/coronavirus/country/uk/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("MalformedURL----------------------------------------------");
        }
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null;) {
                builder.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException----------------------------------------------");
        } finally {
            if (reader != null) try {reader.close();} catch (IOException ignored) {System.out.println("Ignored----------------------------------------------");}
        }

        String start = "<title>United Kingdom Coronavirus: ";
        String end = "- Worldometer";
        String part = builder.substring(builder.indexOf(start) + start.length());
        String actual = part.substring(0, part.indexOf(end));
        System.out.println(actual);
        String extracting = actual.substring(0, 5);
        extracting = extracting.replace(",","");
        System.out.println(extracting);

    }
}
