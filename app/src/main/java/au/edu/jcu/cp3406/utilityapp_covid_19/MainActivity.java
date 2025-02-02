package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public String selectedCountry = "United Kingdom"; //DEFAULT
    public Country currentCountry = new Country();
    public boolean settings;
    public boolean focus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            selectedCountry = savedInstanceState.getString("country");
        }
        assert selectedCountry != null; //To remove warning
        currentCountry.setInfo(selectedCountry);
        new getNewInfo().execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("country", selectedCountry);

        if (!settings && focus) {
            Intent intent = new Intent(this, GraphActivity.class);
            intent.putExtra("country", selectedCountry);
            intent.putExtra("graph", currentCountry.getGraph());
            startActivityForResult(intent, GraphActivity.SETTINGS_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    settings = false;
                    selectedCountry = data.getStringExtra("country");
                    assert selectedCountry != null; //To remove warning
                    currentCountry.setInfo(selectedCountry);
                    new getNewInfo().execute();
                }
            }
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        focus = hasFocus;
    }

    public void settingsCLicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("country", selectedCountry);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);

        settings = true;
    }

    private void setDisplay() {
        TextView title = findViewById(R.id.title);
        TextView cases = findViewById(R.id.casesDisplay);
        TextView deaths = findViewById(R.id.deathsDisplay);
        TextView recovered = findViewById(R.id.recoveredDisplay);
        ImageView image = findViewById(R.id.imageView);

        title.setText(selectedCountry);
        cases.setText(String.format("Confirmed Cases\n%s", currentCountry.getCases()));
        deaths.setText(String.format("Deaths\n%s", currentCountry.getDeaths()));
        recovered.setText(String.format("Recovered\n%s", currentCountry.getRecovered()));
        image.setImageResource(currentCountry.getImage());
    }

    //Get updated numbers from worldometers.info/coronavirus
    @SuppressLint("StaticFieldLeak")
    public class getNewInfo extends AsyncTask<Void,Void,Void> {
        String rawData;
        String relevant;
        String[] data;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(currentCountry.getUrl()).get();
                rawData = doc.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String start = selectedCountry + " Coronavirus Cases: ";
            String end;
            if (selectedCountry.equals("United States")) { end = "Now";
            } else { end = "Active Cases"; }
            if (rawData.length() != 0) {
                relevant = rawData.substring(rawData.indexOf(start) + start.length() - 7);
                relevant = relevant.substring(0, relevant.indexOf(end));
                data = relevant.split(" ", 6);
                currentCountry.setCases(data[1]);
                currentCountry.setDeaths(data[3]);
                currentCountry.setRecovered(data[5]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setDisplay();
        }
    }
}
