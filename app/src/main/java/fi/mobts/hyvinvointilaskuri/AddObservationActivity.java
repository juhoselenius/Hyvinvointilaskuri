package fi.mobts.hyvinvointilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddObservationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String chosenActivity = intent.getStringExtra(ObservationActivity.EXTRA);
        switch (chosenActivity) {
            case "weight":
                setContentView(R.layout.activity_add_weight);
                break;
            case "height":
                setContentView(R.layout.activity_add_height);
                break;
            case "meal":
                setContentView(R.layout.activity_add_meal);
                break;
        }

    }
}