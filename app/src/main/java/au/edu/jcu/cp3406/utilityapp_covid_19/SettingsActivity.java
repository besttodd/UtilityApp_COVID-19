package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    public static int SETTINGS_REQUEST = 444;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void doneClicked(View view) {
        Intent intent = new Intent();
        //intent.putExtra("speed", speed);
        setResult(RESULT_OK, intent);
        finish();
    }
}
