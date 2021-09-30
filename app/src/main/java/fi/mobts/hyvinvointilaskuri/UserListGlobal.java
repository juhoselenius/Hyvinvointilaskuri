package fi.mobts.hyvinvointilaskuri;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import fi.mobts.hyvinvointilaskuri.classes.Observation;

public class UserListGlobal {
    private static final UserListGlobal ourInstance = new UserListGlobal();
    private HashMap<String, ArrayList<Observation>> usersHashMap;
    private String currentUser;

    public static UserListGlobal getInstance() {
        return ourInstance;
    }

    private UserListGlobal() {
        usersHashMap = new HashMap<>();

    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        Log.d("Jorma", "Käyttäjä asetettu");

    }

    public void addObservation(String name, Observation observation) {
        usersHashMap.get(name).add(observation);
        Log.d("Jorma", "Havainto lisätty " + name);
    }

    public void newUser(String name) {
        if (!usersHashMap.containsKey(name)) {
            usersHashMap.put(name, new ArrayList<>());
            Log.d("Jorma", "Hashmappiin lisätty " + name);
        }
    }
}
