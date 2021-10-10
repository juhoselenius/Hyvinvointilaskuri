package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fi.mobts.hyvinvointilaskuri.R;

/**
 * the class <code>ObservationActivity</code> is used to launch the selected observation activity
 * It currently contains only two working observation activities that the user can go to
 * @author Tommi Uponen, Olli Varila, Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class ObservationActivity extends AppCompatActivity {
    public static final String EXTRA = "fi.mobts.hyvinvointilaskuri.EXTRA";
    private TextView textViewComingSoon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
        textViewComingSoon = findViewById(R.id.textViewComingSoon);


    }

    /**
     * The method is used to launch an activity for adding a new weight observation
     * @param v indicates the view which triggered the method call
     */

    public void buttonPaino(View v) {
        Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "weight");
        startActivity(intent);
    }

    /**
     * The method is used to launch an activity for adding a new height observation
     * @param v indicates the view which triggered the method call
     */

    public void buttonPituus(View v) {
        Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "height");
        startActivity(intent);
    }

    /**
     * The method is used for nothing at the current version because of time limits
     * @param v indicates the view which triggered the method call
     */

    public void buttonRuokailu(View v) {
        textViewComingSoon.setVisibility(View.VISIBLE);
        /*Intent intent = new Intent(this, AddObservationActivity.class);
        intent.putExtra(EXTRA, "meal");
        startActivity(intent);*/

    }


}