package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.Toast;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.classes.GraphViewFun;
import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.User;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * The class <code>MainActivity</code> is the main activity of the application.
 * It serves as the main user interface for the application from where other activities are launched from.
 * @author Tommi Uponen, Olli Varila and Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class MainActivity extends AppCompatActivity {
    private GraphView userGraphView;
    private Button addUserButton;
    private Button addObservationButton;
    private Button deleteUserButton;
    private TextView bmiValue;
    private Spinner spinnerAddUser;
    /*private Spinner spinnerGraphs;*/
    private LinearLayout userLayout;
    private String lastSavedData;
    private SimpleDateFormat sdf;

    /**
     * The method is used to create the activity and sets up the UI layout and components.
     * It also fetches the previously saved data from the memory.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUserButton = findViewById(R.id.buttonAddUser);
        addObservationButton = findViewById(R.id.buttonAddObservation);
        bmiValue = findViewById(R.id.textViewBmiValue);
        spinnerAddUser = findViewById(R.id.spinnerAddUser);
        /*spinnerGraphs = findViewById(R.id.spinnerGraphs);*/
        userLayout = findViewById(R.id.linearLayout);
        userGraphView = findViewById(R.id.userGraphView);
        deleteUserButton = findViewById(R.id.buttonDeleteUser);
        userGraphView = findViewById(R.id.userGraphView);
        /*
        SharedPreferences prefClear = getSharedPreferences("AppPref" , Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefClear.edit();
        prefEditor.clear();
        prefEditor.commit();
        */

        SharedPreferences prefGet = getSharedPreferences("AppPref" , Activity.MODE_PRIVATE);
        lastSavedData = prefGet.getString("PrefKeyHashMap", "{}");
        String savedCurrentUser = prefGet.getString("PrefKeyCurrentUser", "Ei käyttäjää");

        if(!lastSavedData.equals("")) {
            LinkedHashMap<String, ArrayList<Observation>> savedData = UserListGlobal.getInstance().appDataFromGson(lastSavedData);
            UserListGlobal.getInstance().setUsersHashMap(savedData);
            UserListGlobal.getInstance().setCurrentUser(savedCurrentUser);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UserListGlobal.getInstance().getDropdownUsers());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddUser.setAdapter(adapter);

        if (!(UserListGlobal.getInstance().getUsers().contains("Ei käyttäjää"))) {
            addObservationButton.setVisibility(View.VISIBLE);
            /*spinnerGraphs.setVisibility(View.VISIBLE);*/
            userLayout.setVisibility(View.VISIBLE);
            userGraphView.setVisibility(View.VISIBLE);
            deleteUserButton.setVisibility(View.VISIBLE);
            bmiValue.setText(String.format("%.3g%n", UserListGlobal.getInstance().currentBMI()));
            deleteUserButton.setVisibility(View.VISIBLE);

            spinnerAddUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ArrayList<String> userList = UserListGlobal.getInstance().getUserListSnapShot();
                    String newCurrentUser = userList.get(i);

                    Log.d("Jorma", userList.get(i) + " paikalla " + i);

                    UserListGlobal.getInstance().setCurrentUser(newCurrentUser);
                    bmiValue.setText(String.format("%.3g%n", UserListGlobal.getInstance().currentBMI()));

                    Log.d("Jorma", "Nykyinen käyttäjä: "+UserListGlobal.getInstance().getCurrentUser());

                    sdf = new SimpleDateFormat("dd.MM.");
                    GraphViewFun gvf = new GraphViewFun();
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(gvf.getweightDatapoints());
                    series.setColor(Color.BLUE);
                    series.setDrawDataPoints(true);
                    series.setDataPointsRadius(10);
                    series.setThickness(8);

                    userGraphView.setTitle("Paino");
                    userGraphView.setTitleColor(R.color.purple_200);
                    userGraphView.setTitleTextSize(48);
                    userGraphView.removeAllSeries();
                    userGraphView.addSeries(series);

                    if(UserListGlobal.getInstance().getWeightsList(UserListGlobal.getInstance().getCurrentUser()).size() > 1) {
                        userGraphView.getViewport().setXAxisBoundsManual(true);
                        userGraphView.getViewport().setYAxisBoundsManual(true);
                        userGraphView.getViewport().setMaxX(UserListGlobal.getInstance().getMaxWeightDate());
                        userGraphView.getViewport().setMinX(UserListGlobal.getInstance().getMinWeightDate());
                        userGraphView.getViewport().setMaxY(UserListGlobal.getInstance().getMaxWeight());
                        userGraphView.getViewport().setMinY(UserListGlobal.getInstance().getMinWeight());
                    }

                    series.setOnDataPointTapListener(new OnDataPointTapListener() {
                        @Override
                        public void onTap(Series series, DataPointInterface dataPoint) {
                            SimpleDateFormat weightDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                            Toast.makeText(getApplicationContext(), "Paino " + "\n" + weightDate.format(dataPoint.getX()) + ":\n" + dataPoint.getY() + " kg", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Log.d("Jorma", "Datapointit: "+ series);

                    userGraphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                        public String formatLabel(double value, boolean isValueX) {
                            if(isValueX) {
                                return sdf.format(new Date((long) value));
                            } else {
                                return super.formatLabel(value, isValueX);
                            }
                        }
                    });
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    /**
     * The method is used to access <code>ObservationActivity</code>.
     * It is called, when the user taps the Add Observation button.
     * @param v The view of the activity.
     */

    public void observationButton(View v) {
        Intent intent = new Intent(this, ObservationActivity.class);

        startActivity(intent);
    }

    /**
     * The method is used to access <code>DeleteUserActivity</code>.
     * It is called, when the user taps the Delete Observation button.
     * @param v The view of the activity.
     */

    public void deleteUserButton(View v) {
        Intent intent = new Intent(this, DeleteUserActivity.class);

        startActivity(intent);
    }

    /**
     * The method is used to access <code>AddUserActivity</code>.
     * It is called, when the user taps the Add User button.
     * @param v The view of the activity.
     */

    public void userButton(View v) {
        Intent intent = new Intent(this, AddUserActivity.class);

        startActivity(intent);
    }

    /**
     * The method is used to access <code>BodyMassIndexInfoActivity</code>.
     * It is called, when the user taps the Body Mass Index textview.
     * @param v The view of the activity.
     */

    public void bmiText(View v) {
        Intent intent = new Intent(this, BodyMassIndexInfoActivity.class);

        startActivity(intent);
    }

    /**
     * The method is used to save application data, when application is paused.
     */

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("HyteApp", "MainActivity onPause()");

        SharedPreferences prefPut = getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("PrefKeyHashMap", UserListGlobal.getInstance().appDataToGson());
        prefEditor.putString("PrefKeyCurrentUser", UserListGlobal.getInstance().getCurrentUser());

        prefEditor.commit();
    }

}
