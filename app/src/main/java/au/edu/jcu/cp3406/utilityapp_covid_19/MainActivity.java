package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {
    public String selectedCountry = "Australia"; //DEFAULT
    public Country currentCountry = new Country();
    public boolean settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            selectedCountry = savedInstanceState.getString("country");
        }
        currentCountry.setInfo(selectedCountry);
        setDisplay();

        System.out.println("On CREATE------------------------------------------"+selectedCountry);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("country", selectedCountry);
        System.out.println(settings+"------------------------------------------------------------");
        if (!settings) {
            Intent intent = new Intent(this, GraphActivity.class);
            intent.putExtra("country", selectedCountry);
            startActivityForResult(intent, GraphActivity.SETTINGS_REQUEST);
        }

        System.out.println("SAVE INSTANCE------------------------------------------"+selectedCountry);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    settings = false;
                    selectedCountry = data.getStringExtra("country");
                    currentCountry.setInfo(selectedCountry);
                    setDisplay();
                    System.out.println("Activity RESULT------------------------------------------"+selectedCountry);
                }
            }
        }
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
        cases.setText(String.format("Confirmed Cases\n%s", Integer.toString(currentCountry.getCases())));
        deaths.setText(String.format("Deaths\n%s", Integer.toString(currentCountry.getDeaths())));
        recovered.setText(String.format("Recovered\n%s", Integer.toString(currentCountry.getRecovered())));
        image.setImageResource(currentCountry.getImage());
    }
}
