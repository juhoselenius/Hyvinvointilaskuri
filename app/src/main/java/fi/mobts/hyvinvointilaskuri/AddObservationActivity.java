package fi.mobts.hyvinvointilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddObservationActivity extends AppCompatActivity {
    private EditText editTextAddWeight;
    private Button buttonAddWeight;

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

    public void addWeight(View v) {
        editTextAddWeight = findViewById(R.id.editTextWeight);

        double weight = Double.parseDouble(String.valueOf(editTextAddWeight.getText()));

        WeightDataGlobal.getInstance().addWeight(weight);
    }
}