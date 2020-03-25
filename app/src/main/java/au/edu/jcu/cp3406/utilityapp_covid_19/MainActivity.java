package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] countryList = { "United Kingdom", "United States", "Australia", "Italy", "China"};
    String selectedCountry;
    Country currentCountry = new Country();
    boolean settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner country = findViewById(R.id.country);
        country.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(aa);

        currentCountry.setInfo("selectedCountry");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("country", selectedCountry);
        System.out.println(settings+"------------------------------------------------------------");
        if (!settings) {
            Intent intent = new Intent(this, GraphActivity.class);
            //intent.putExtra("speed", stopwatch.getSpeed());
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
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long id) {
        selectedCountry = parent.getItemAtPosition(pos).toString();
        setDisplay();
        setImage(selectedCountry);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //
    }

    public void settingsCLicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        //intent.putExtra("speed", stopwatch.getSpeed());
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);

        settings = true;
    }

    private void setDisplay() {
        TextView cases = findViewById(R.id.casesDisplay);
        TextView deaths = findViewById(R.id.deathsDisplay);
        TextView recovered = findViewById(R.id.recoveredDisplay);

        cases.setText(String.format("Confirmed Cases\n%s", Integer.toString(currentCountry.getCases())));
        deaths.setText(String.format("Confirmed Cases\n%s", Integer.toString(currentCountry.getDeaths())));
        recovered.setText(String.format("Confirmed Cases\n%s", Integer.toString(currentCountry.getRecovered())));
    }

    private void setImage(String country) {
        ImageView image = findViewById(R.id.imageView);

        switch (country) {
            case "United Kingdom":
                image.setImageResource(R.drawable.uk);
                break;
            case "United States":
                image.setImageResource(R.drawable.usa);
                break;
            case "Australia":
                image.setImageResource(R.drawable.aus);
                break;
            case "Italy":
                image.setImageResource(R.drawable.italy);
                break;
            case "China":
                image.setImageResource(R.drawable.china);
                break;
        }
    }
}
