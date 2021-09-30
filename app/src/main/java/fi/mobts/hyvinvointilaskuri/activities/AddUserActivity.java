package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.classes.HeightObservation;
import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.User;
import fi.mobts.hyvinvointilaskuri.classes.WeightObservation;

public class AddUserActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userWeight;
    private EditText userHeight;
    private RadioGroup userGender;
    private String gender;
    private String name;
    private double weight;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userName = findViewById(R.id.editTextProfileName);
        userWeight = findViewById(R.id.editTextWeight);
        userHeight = findViewById(R.id.editTextHeight);
        userGender = findViewById(R.id.rgGender);

        Intent intent = getIntent();

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

    public void userAdded(View v) {
        //Tässä validoidaan nimikenttä
        if (userName.getText().toString().isEmpty()) {
            //Tähän jotain koodia, jos nimikenttä on tyhjä
        } else {
            name = userName.getText().toString();
        }

        //Tässä validoidaan painokenttä
        if(userWeight.getText().toString().isEmpty()) {
            //Tähän jotain koodia, jos painokenttä on tyhjä
        } else {
            weight = Double.parseDouble(userWeight.getText().toString());
        }

        //Tässä validoidaan pituuskenttä
        if(userHeight.getText().toString().isEmpty()) {
            //Tähän jotain koodia, jos pituuskenttä on tyhjä
        } else {
            height = Integer.parseInt(userHeight.getText().toString());
        }

        //Tallennetaan sukupuoli-valinta
        if(userGender.getCheckedRadioButtonId() == R.id.rbGenderMale) {
            gender = "male";
        } else if (userGender.getCheckedRadioButtonId() == R.id.rbGenderFemale) {
            gender = "female";
        }

        //Luodaan ensihavainnot
        Observation firstWeight = new WeightObservation(weight, getDate());
        Observation firstHeight = new HeightObservation(height, getDate());


        User user = new User(name, weight, height, gender);
        UserListGlobal.getInstance().setCurrentUser(name);
        UserListGlobal.getInstance().newUser(name);
        UserListGlobal.getInstance().addObservation(name, firstWeight);
        UserListGlobal.getInstance().addObservation(name, firstHeight);
    }

    public Date getDate() {
        return new Date();
    }

}