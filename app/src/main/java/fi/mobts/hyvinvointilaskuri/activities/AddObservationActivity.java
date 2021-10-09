package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.classes.HeightObservation;
import fi.mobts.hyvinvointilaskuri.classes.WeightObservation;

public class AddObservationActivity extends AppCompatActivity {
    private EditText editTextAddWeight;
    private EditText editTextAddHeight;
    private double weight;
    private int height;
    private TextView textView;
    private TextView textView2;

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
                editTextAddHeight = findViewById(R.id.editTextHeightObservation);
                textView2 = findViewById(R.id.textViewHeightObservationValidate);
                break;
            case "meal":
                setContentView(R.layout.activity_add_meal);
                break;
        }

    }

    public void addWeight(View v) {
        boolean isValid = true;
        if (!editTextAddWeight.getText().toString().isEmpty()) {
            weight = Double.parseDouble(editTextAddWeight.getText().toString());
        }
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

            Log.d("Jorma", isValid + "Paino lisätty " + weight + " käyttäjälle " + user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void addHeight(View v) {
        boolean isValid = true;
        if (!editTextAddHeight.getText().toString().isEmpty()) {
            height = Integer.parseInt(editTextAddHeight.getText().toString());
        }
        if (editTextAddHeight.getText().toString().isEmpty()) {
            textView2.setVisibility(View.VISIBLE);
            textView2.setText("Kenttä ei voi olla tyhjä.");
            isValid = false;
        } else if (height > 300 || height < 20) {
            textView2.setVisibility(View.VISIBLE);
            textView2.setText("Pituus pitää olla väliltä 20-300 cm.");
            isValid = false;
        } else {
            textView2.setVisibility(View.INVISIBLE);
        }
        if (isValid) {
            HeightObservation hObservation = new HeightObservation(height, UserListGlobal.getInstance().getDate());
            String user = UserListGlobal.getInstance().getCurrentUser();
            UserListGlobal.getInstance().addObservation(user, hObservation);

            Log.d("Jorma", isValid + "Pituus lisätty " + height + " käyttäjälle " + user);
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