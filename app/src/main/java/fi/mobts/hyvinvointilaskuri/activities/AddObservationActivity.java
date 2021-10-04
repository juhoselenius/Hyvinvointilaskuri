package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.WeightDataGlobal;
import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.WeightObservation;

public class AddObservationActivity extends AppCompatActivity {
    private EditText editTextAddWeight;
    private double weight;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String chosenActivity = intent.getStringExtra(ObservationActivity.EXTRA);
        switch (chosenActivity) {
            case "weight":
                setContentView(R.layout.activity_add_weight);
                editTextAddWeight = findViewById(R.id.editTextWeightObservation);
                textView = findViewById(R.id.textViewWeightObservationValidate);
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
        boolean isValid = true;
        weight = Double.parseDouble(editTextAddWeight.getText().toString());
        if (editTextAddWeight.getText().toString().isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Kenttä ei voi olla tyhjä.");
            isValid = false;
        } else if (weight > 700 || weight < 1) {
            textView.setVisibility(View.VISIBLE);
            textView.setText("Paino pitää olla väliltä 1-700 kg.");
            isValid = false;
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
        if (isValid) {
            WeightObservation wObservation = new WeightObservation(weight, UserListGlobal.getInstance().getDate());
            String user = UserListGlobal.getInstance().getCurrentUser();
            UserListGlobal.getInstance().addObservation(user, wObservation);

            Log.d("Jorma", "Paino lisätty " + weight + " käyttäjälle " + user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("HyteApp", "AddObservationActivity onPause()");

        SharedPreferences prefPut = getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("PrefKeyHashMap", UserListGlobal.getInstance().appDataToGson());
        prefEditor.putString("PrefKeyCurrentUser", UserListGlobal.getInstance().getCurrentUser());

        prefEditor.commit();

        Log.d("HyteApp", "Tiedot tallennettu");
    }
}