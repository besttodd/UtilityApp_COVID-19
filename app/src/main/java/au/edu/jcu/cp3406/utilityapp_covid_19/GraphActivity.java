package au.edu.jcu.cp3406.utilityapp_covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class GraphActivity extends AppCompatActivity {
    public static int SETTINGS_REQUEST = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
