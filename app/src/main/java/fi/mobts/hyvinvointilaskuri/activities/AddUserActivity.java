package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;
import fi.mobts.hyvinvointilaskuri.WeightDataGlobal;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userName = findViewById(R.id.editTextProfileName);
        userWeight = findViewById(R.id.editTextWeight);
        userHeight = findViewById(R.id.editTextHeight);
        userGender = findViewById(R.id.rgGender);

        Intent intent = getIntent();

        userGender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = userGender.getCheckedRadioButtonId();

                if (selectedId == R.id.rbGenderMale) {
                    gender = "male";
                } else if (selectedId == R.id.rbGenderFemale) {
                    gender = "female";
                }

            }

        });
    }

    public void userAdded(View v) {
        String name = userName.getText().toString();
        double weight = Double.parseDouble(userWeight.getText().toString());
        int height = Integer.parseInt(userHeight.getText().toString());
        Observation firstWeight = new WeightObservation(weight, getDate());
        Observation firstHeight = new HeightObservation(height, getDate());


        User user = new User(name, weight, height, gender);
        UserListGlobal.getInstance().setCurrentUser(name);
        UserListGlobal.getInstance().newUser(name);
        UserListGlobal.getInstance().addObservation(name, firstWeight);
        UserListGlobal.getInstance().addObservation(name, firstHeight);
    }

    public Date getDate() {
        Date nyt = new Date();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        return nyt;
    }

}