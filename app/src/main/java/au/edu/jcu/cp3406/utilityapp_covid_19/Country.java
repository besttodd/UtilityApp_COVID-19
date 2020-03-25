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

    void Country () {
        cases = 0;
        deaths = 0;
        recovered = 0;
        String updatedData = "None";
    }

    public void updateInfo(String newData) {
        //Get updated numbers from worldometers.info/coronavirus
        cases = getCases();
        deaths = 281;
        recovered = 93;
    }

    int getCases() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                InputStream is = null;
                BufferedReader br = null;
                StringBuilder builder = new StringBuilder();

                try{
                    url = new URL("https://www.worldometers.info/coronavirus/country/uk/");
                    is = url.openStream();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                    for (String line; (line = br.readLine()) != null;) { builder.append(line.trim()); }

                } catch (MalformedURLException mue) {
                    mue.printStackTrace();
                    System.out.println("MalformedURL22----------------------------------------------");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    System.out.println("IOException22----------------------------------------------");
                } finally {
                    try {
                        if (is != null) is.close();
                    } catch (IOException ioe) {
                        //
                    }
                }

                /*try {
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
                }*/

                String start = "<title>United Kingdom Coronavirus: ";
                String end = "- Worldometer";
                String part = builder.substring(builder.indexOf(start) + start.length());
                String actual = part.substring(0, part.indexOf(end));
                System.out.println(actual);
                /*String extracting = actual.substring(0, 5);
                extracting = extracting.replace(",","");
                System.out.println(extracting);*/
            }
        }).start();

        /*String extracting = updatedData.substring(0, 5);
        extracting = extracting.replace(",","");
        System.out.println(extracting);
        cases = Integer.parseInt(extracting);*/
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }
}
