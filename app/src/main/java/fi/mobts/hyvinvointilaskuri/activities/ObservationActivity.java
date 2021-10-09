package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;

public class ObservationActivity extends AppCompatActivity {
    public static final String EXTRA = "fi.mobts.hyvinvointilaskuri.EXTRA";
    private TextView textViewComingSoon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
        textViewComingSoon = findViewById(R.id.textViewComingSoon);


    }

    public void buttonPaino(View v) {
        Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "weight");
        startActivity(intent);
    }

    public void buttonPituus(View v) {
        Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "height");
        startActivity(intent);
    }

    public void buttonRuokailu(View v) {
        textViewComingSoon.setVisibility(View.VISIBLE);
        /*Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "meal");
        startActivity(intent);*/

    }


}