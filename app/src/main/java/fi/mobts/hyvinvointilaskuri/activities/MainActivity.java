package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import fi.mobts.hyvinvointilaskuri.classes.Observation;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GraphView userGraphView;
    private Button addUserButton;
    private Button addObservationButton;
    private Button deleteUserButton;
    private TextView bmiValue;
    private Spinner spinnerAddUser;
    private Spinner spinnerGraphs;
    private LinearLayout userLayout;
    private String lastSavedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUserButton = findViewById(R.id.buttonAddUser);
        addObservationButton = findViewById(R.id.buttonAddObservation);
        bmiValue = findViewById(R.id.textViewBmiValue);
        spinnerAddUser = findViewById(R.id.spinnerAddUser);
        spinnerGraphs = findViewById(R.id.spinnerGraphs);
        userLayout = findViewById(R.id.linearLayout);
        userGraphView = findViewById(R.id.userGraphView);

        deleteUserButton = findViewById(R.id.buttonDeleteUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserListGlobal.getInstance().getUsers());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddUser.setAdapter(adapter);

        SharedPreferences prefGet = getSharedPreferences("AppPref" , Activity.MODE_PRIVATE);
        lastSavedData = prefGet.getString("PrefKeyHashMap", "");

        //Tämä pätkä koodia kaataa sovelluksen käynnistyessä
        /*if(!lastSavedData.equals("")) {
            HashMap <String, ArrayList<Observation>> savedData = UserListGlobal.getInstance().appDataFromGson(lastSavedData);
            UserListGlobal.getInstance().setUsersHashMap(savedData);
        }*/

        if (!(UserListGlobal.getInstance().getCurrentUser() == null)) {
            /*addUserButton.setText("Valittu: " + UserListGlobal.getInstance().getCurrentUser());*/
            addObservationButton.setVisibility(View.VISIBLE);
            spinnerGraphs.setVisibility(View.VISIBLE);
            userLayout.setVisibility(View.VISIBLE);
            userGraphView.setVisibility(View.VISIBLE);
            bmiValue.setText(String.format("%.3g%n", UserListGlobal.getInstance().currentBMI()));
            deleteUserButton.setVisibility(View.VISIBLE);

            spinnerAddUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ArrayList<String> userList = UserListGlobal.getInstance().getUsers();
                    String newCurrentUser = userList.get(i);
                    if (i == 0) {
                    }

                    UserListGlobal.getInstance().setCurrentUser(newCurrentUser);
                    /*addUserButton.setText("Valittu: " + newCurrentUser);*/
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }


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

    public void deleteUserButton(View v) {
        Intent intent = new Intent(this, DeleteUserActivity.class);

        startActivity(intent);
    }

    public void userButton(View v) {
        Intent intent = new Intent(this, AddUserActivity.class);

        startActivity(intent);
        Log.d("HyteApp", "Siirrytty lisäämään käyttäjää");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("HyteApp", "MainActivity onPause()");

        SharedPreferences prefPut = getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("PrefKeyHashMap", UserListGlobal.getInstance().appDataToGson());

        prefEditor.commit();

        Log.d("HyteApp", "Tiedot tallennettu");

    }

}
