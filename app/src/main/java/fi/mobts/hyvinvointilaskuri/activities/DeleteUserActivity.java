package fi.mobts.hyvinvointilaskuri.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fi.mobts.hyvinvointilaskuri.R;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;


public class DeleteUserActivity extends AppCompatActivity {
private ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        lw = findViewById(R.id.listViewUsers);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, UserListGlobal.getInstance().getUsers());
        lw.setAdapter(adapter);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String user = UserListGlobal.getInstance().getUsers().get(i);
                UserListGlobal.getInstance().removeUser(user);
                Log.d("Jorma", "Removed " + user);

                toMain();
            }
        });
    }
    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}