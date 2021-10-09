package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;

/**
 * The class <code>BodyMassIndexInfoActivity</code> is an application activity, where BMI info of the current user can be viewed.
 * @author Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class BodyMassIndexInfoActivity extends AppCompatActivity {

    /**
     * The method is used to create the activity and set up the UI layout and components.
     * It shows the table for BMI ranges, current BMI and where it is situated in the table.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_mass_index_info);

        TextView bmiText = findViewById(R.id.textViewBodyMassIndex);
        TextView bmiInfo = findViewById(R.id.textViewBmiInfo);

        bmiText.setText("Nykyinen BMI: "+String.format("%.3g%n", UserListGlobal.getInstance().currentBMI()));

        if(UserListGlobal.getInstance().currentBMI() < 15.0) {
            bmiInfo.setText(R.string.bmi_0_15);
        } else if (UserListGlobal.getInstance().currentBMI() < 17.0) {
            bmiInfo.setText(R.string.bmi_15_17);
        } else if (UserListGlobal.getInstance().currentBMI() < 18.5) {
            bmiInfo.setText(R.string.bmi_17_185);
        } else if (UserListGlobal.getInstance().currentBMI() < 25) {
            bmiInfo.setText(R.string.bmi_185_25);
        } else if (UserListGlobal.getInstance().currentBMI() < 30) {
            bmiInfo.setText(R.string.bmi_25_30);
        } else if (UserListGlobal.getInstance().currentBMI() < 35) {
            bmiInfo.setText(R.string.bmi_30_35);
        } else if (UserListGlobal.getInstance().currentBMI() < 40) {
            bmiInfo.setText(R.string.bmi_35_40);
        } else {
            bmiInfo.setText(R.string.bmi_40_100);
        }
    }
}