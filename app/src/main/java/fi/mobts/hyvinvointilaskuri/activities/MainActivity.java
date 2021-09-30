package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fi.mobts.hyvinvointilaskuri.R;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GraphView graphViewBMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*graphViewBMI = findViewById(R.id.graphViewBMI);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

        });

        graphViewBMI.setTitle("Paino");
        graphViewBMI.setTitleColor(R.color.purple_200);
        graphViewBMI.setTitleTextSize(18);
        graphViewBMI.addSeries(series); */

    }

    public void observationButton(View v) {
        Intent intent = new Intent(this, ObservationActivity.class);

        startActivity(intent);
    }

    public void userButton(View v) {
        Intent intent = new Intent(this, AddUserActivity.class);

        startActivity(intent);
        Log.d("Jorma", "Siirrytty lisäämään käyttäjää");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
