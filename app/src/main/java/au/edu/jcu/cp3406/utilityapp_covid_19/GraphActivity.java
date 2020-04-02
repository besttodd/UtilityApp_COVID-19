package au.edu.jcu.cp3406.utilityapp_covid_19;

/*
This activity displays a scrollable screen with 2 graph for the selected country.
Graphs are NOT up to date. They do NOT represent current figures.
Implementation coming soon..........
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

public class GraphActivity extends AppCompatActivity {
    public static int SETTINGS_REQUEST = 222;
    int countryGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        countryGraph = Objects.requireNonNull(getIntent().getExtras()).getInt("graph");
        ImageView graph = findViewById(R.id.graphView);
        graph.setImageResource(countryGraph);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
