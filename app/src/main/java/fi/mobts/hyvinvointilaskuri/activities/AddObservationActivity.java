package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.WeightDataGlobal;
import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.WeightObservation;

public class AddObservationActivity extends AppCompatActivity {
    private EditText editTextAddWeight;
    private Button buttonAddWeight;
    private double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String chosenActivity = intent.getStringExtra(ObservationActivity.EXTRA);
        switch (chosenActivity) {
            case "weight":
                setContentView(R.layout.activity_add_weight);
                editTextAddWeight = findViewById(R.id.editTextWeightObservation);
                break;
            case "height":
                setContentView(R.layout.activity_add_height);
                break;
            case "meal":
                setContentView(R.layout.activity_add_meal);
                break;
        }

    }

    public void addWeight(View v) {
        weight = Double.parseDouble(editTextAddWeight.getText().toString());
        WeightObservation wObservation = new WeightObservation(weight, UserListGlobal.getInstance().getDate());
        String user = UserListGlobal.getInstance().getCurrentUser();
        UserListGlobal.getInstance().addObservation(user, wObservation);

        Log.d("Jorma", "Paino lisätty " + weight + " käyttäjälle " + user);
    }
}