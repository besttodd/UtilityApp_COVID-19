package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {
    public static int SETTINGS_REQUEST = 444;
    public String existingCountry;
    public String newCountry;
    Spinner selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        existingCountry = Objects.requireNonNull(getIntent().getExtras()).getString("country");
        selectedCountry = findViewById(R.id.country);
        selectedCountry.setSelection(getIndex(selectedCountry, existingCountry));
    }

    public void doneClicked(View view) {
        selectedCountry = findViewById(R.id.country);
        newCountry = String.valueOf(selectedCountry.getSelectedItem());
        Intent intent = new Intent();
        intent.putExtra("country", String.valueOf(selectedCountry.getSelectedItem()));
        setResult(RESULT_OK, intent);
        finish();
    }

    private int getIndex(Spinner spinner, String country) {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(country)) {index = i;}
        }
        return index;
    }
}
