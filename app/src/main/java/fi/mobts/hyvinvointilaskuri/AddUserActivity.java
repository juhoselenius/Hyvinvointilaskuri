package fi.mobts.hyvinvointilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextWeight;
    private EditText editTextHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextName = findViewById(R.id.editTextProfileName);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        Intent intent = getIntent();
    }
}