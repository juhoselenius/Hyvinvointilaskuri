package fi.mobts.hyvinvointilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
                }
                else if (selectedId == R.id.rbGenderFemale) {
                    gender = "female";
                }

            }

        });
    }

    public void userAdded(View v) {
    String name = userName.getText().toString();
    int weight = Integer.parseInt(userWeight.getText().toString());
    int height = Integer.parseInt(userHeight.getText().toString());

    UserClass user = new UserClass(name, weight, height, gender);
    }

}