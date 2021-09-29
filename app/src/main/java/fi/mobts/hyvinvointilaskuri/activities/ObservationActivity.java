package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fi.mobts.hyvinvointilaskuri.R;

public class ObservationActivity extends AppCompatActivity {
    public static final String EXTRA = "fi.mobts.hyvinvointilaskuri.EXTRA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);


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
        Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "meal");
        startActivity(intent);
    }



}