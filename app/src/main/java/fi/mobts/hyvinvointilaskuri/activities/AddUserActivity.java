package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.classes.HeightObservation;
import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.User;
import fi.mobts.hyvinvointilaskuri.classes.WeightObservation;

/**
 * The class <code>AddUserActivity</code> is used to create a new user profile for the application
 * It contains all needed data for creating a new profile in the activity
 * @author Tommi Uponen, Olli Varila, Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class AddUserActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userWeight;
    private EditText userHeight;
    private RadioGroup userGender;
    private String gender;
    private String name;
    private double weight;
    private int height;
    private TextView validateName;
    private TextView validateHeight;
    private TextView validateWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userName = findViewById(R.id.editTextProfileName);
        userWeight = findViewById(R.id.editTextWeight);
        userHeight = findViewById(R.id.editTextHeight);
        userGender = findViewById(R.id.rgGender);
        validateName = findViewById(R.id.textViewNameValidateMessage);
        validateHeight = findViewById(R.id.textViewHeightValidateMessage);
        validateWeight = findViewById(R.id.textViewWeightValidateMessage);

        Intent intent = getIntent();

        //luultavasti ei tule enää käyttöön
        /*userGender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = userGender.getCheckedRadioButtonId();

                if (selectedId == R.id.rbGenderMale) {
                    gender = "male";
                } else if (selectedId == R.id.rbGenderFemale) {
                    gender = "female";
                }

            }

        });*/
    }

    /**
     * The method is used to add the created userprofile
     * It is also used to validate all the collected data before adding the created user
     * If the data is valid the method creates new observations for weight and height and starts MainActivity
     * @param v indicates the view which triggered the method call
     */

    public void userAdded(View v) {

        boolean isValid = true;
        if (!(userWeight.getText().toString().isEmpty() || userHeight.getText().toString().isEmpty())) {
            weight = Double.parseDouble(userWeight.getText().toString());
            height = Integer.parseInt(userHeight.getText().toString());
        }

        //Tässä validoidaan nimikenttä
        if (userName.getText().toString().isEmpty()) {
            validateName.setVisibility(View.VISIBLE);
            validateName.setText("Kenttä ei voi olla tyhjä.");
            isValid = false;
        } else {
            name = userName.getText().toString();
            validateName.setVisibility(View.INVISIBLE);
        }

        //Tässä validoidaan painokenttä
        if (userWeight.getText().toString().isEmpty()) {
            validateWeight.setVisibility(View.VISIBLE);
            validateWeight.setText("Kenttä ei voi olla tyhjä.");
            isValid = false;
        } else if (weight > 700 || weight < 1) {
            validateWeight.setVisibility(View.VISIBLE);
            validateWeight.setText("Paino pitää olla väliltä 1-700 kg.");
            isValid = false;
        } else {
            validateWeight.setVisibility(View.INVISIBLE);
        }

        //Tässä validoidaan pituuskenttä
        if (userHeight.getText().toString().isEmpty()) {
            validateHeight.setVisibility(View.VISIBLE);
            validateHeight.setText("Kenttä ei voi olla tyhjä.");
            isValid = false;
        } else if (height > 300 || height < 20) {
            validateHeight.setVisibility(View.VISIBLE);
            validateHeight.setText("Pituus pitää olla väliltä 20-300 cm.");
            isValid = false;
        } else {
            validateHeight.setVisibility(View.INVISIBLE);
        }

        //Tallennetaan sukupuoli-valinta
        if (userGender.getCheckedRadioButtonId() == R.id.rbGenderMale) {
            gender = "male";
        } else if (userGender.getCheckedRadioButtonId() == R.id.rbGenderFemale) {
            gender = "female";
        }

        //Luodaan ensihavainnot
        if (isValid == true) {
            Observation firstWeight = new WeightObservation(weight, UserListGlobal.getInstance().getDate());
            Observation firstHeight = new HeightObservation(height, UserListGlobal.getInstance().getDate());


            User user = new User(name, weight, height, gender);
            UserListGlobal.getInstance().newUser(name);
            UserListGlobal.getInstance().setCurrentUser(name);
            UserListGlobal.getInstance().addObservation(name, firstWeight);
            UserListGlobal.getInstance().addObservation(name, firstHeight);

            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        }
    }

    /**
     * The method is used to save application data, when application is paused.
     */

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("HyteApp", "AddUserActivity onPause()");

        SharedPreferences prefPut = getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("PrefKeyHashMap", UserListGlobal.getInstance().appDataToGson());
        prefEditor.putString("PrefKeyCurrentUser", UserListGlobal.getInstance().getCurrentUser());

        prefEditor.commit();

        Log.d("HyteApp", "Tiedot tallennettu");
    }
}