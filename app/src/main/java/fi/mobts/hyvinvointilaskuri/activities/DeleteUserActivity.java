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
import android.widget.ListView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;

/**
 * the class <code>DeleteUserActivity</code> is used for deleting a selected user
 * Starts an activity with a listview where you can delete any created user profile
 * @author Tommi Uponen, Olli Varila, Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class DeleteUserActivity extends AppCompatActivity {
private ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        lw = findViewById(R.id.listViewUsers);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.deleteuser_item_layout, UserListGlobal.getInstance().getUsers());
        lw.setAdapter(adapter);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String user = UserListGlobal.getInstance().getUsers().get(i);
                UserListGlobal.getInstance().removeUser(user);

                toMain();
            }
        });
    }

    /**
     * The method is used to return to the main activity after deleting a user
     */

    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * The method is used to save application data, when application is paused.
     */

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("HyteApp", "DeleteUserActivity onPause()");

        SharedPreferences prefPut = getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("PrefKeyHashMap", UserListGlobal.getInstance().appDataToGson());
        prefEditor.putString("PrefKeyCurrentUser", UserListGlobal.getInstance().getCurrentUser());

        prefEditor.commit();

        Log.d("HyteApp", "Tiedot tallennettu");
    }
}