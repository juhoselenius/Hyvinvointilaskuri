package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GraphView graphViewBMI;
    private Button addUserButton;
    private Button addObservationButton;
    private TextView bmiValue;
    private ScrollView scrollView;
    private Spinner spinnerAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUserButton = findViewById(R.id.buttonAddUser);
        addObservationButton = findViewById(R.id.buttonAddObservation);
        scrollView = findViewById(R.id.scrollView2);
        bmiValue = findViewById(R.id.textViewBmiValue);
        spinnerAddUser = findViewById(R.id.spinnerAddUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserListGlobal.getInstance().getUsers());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddUser.setAdapter(adapter);

        /*spinnerAddUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        /*if (!(UserListGlobal.getInstance().getCurrentUser() == null)) {
            addUserButton.setText("Valittu käyttäjä: " + UserListGlobal.getInstance().getCurrentUser());
            addObservationButton.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            bmiValue.setText(String.format("%.3g%n", UserListGlobal.getInstance().currentBMI()));
        }*/
    }

        /*graphViewBMI = findViewById(R.id.graphViewBMI);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

        });

        graphViewBMI.setTitle("Paino");
        graphViewBMI.setTitleColor(R.color.purple_200);
        graphViewBMI.setTitleTextSize(18);
        graphViewBMI.addSeries(series); */


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
