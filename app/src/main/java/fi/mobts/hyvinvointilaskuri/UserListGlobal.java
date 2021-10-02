package fi.mobts.hyvinvointilaskuri;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fi.mobts.hyvinvointilaskuri.classes.Observation;

public class UserListGlobal {
    private static final UserListGlobal ourInstance = new UserListGlobal();
    private static final TypeToken token = new TypeToken<HashMap<String,ArrayList<Observation>>>(){};
    private HashMap<String, ArrayList<Observation>> usersHashMap;
    private String currentUser;
    private Gson gson;
    private String json;

    public static UserListGlobal getInstance() {
        return ourInstance;
    }

    private UserListGlobal() {
        usersHashMap = new HashMap<>();
        gson = new Gson();

    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        Log.d("Jorma", "Käyttäjä asetettu " + currentUser);

    }

    public double getCurrentWeight() {
        ArrayList<Observation> observations = usersHashMap.get(currentUser);
        double currentWeight = 1.0;
        for (int i = observations.size() - 1; i >= 0; i--) {
            if (observations.get(i).getType().equals("weight")) {
                currentWeight = observations.get(i).getWeight();
                break;
            }
        }
        Log.d("Jorma", "CurrentWeight " + currentWeight);
        return currentWeight;
    }

    public double getCurrentHeight() {
        ArrayList<Observation> observations = usersHashMap.get(currentUser);
        int currentHeight = 0;
        for (int i = observations.size() - 1; i >= 0; i--) {
            if (observations.get(i).getType().equals("height")) {
                currentHeight = observations.get(i).getHeight();
                break;
            }
        }
        Log.d("Jorma", "CurrentHeight " + currentHeight);
        return currentHeight;
    }

    public double currentBMI() {
        double currentHeight = getCurrentHeight() / 100;
        double currentBMI = getCurrentWeight() / (currentHeight * currentHeight);
        Log.d("Jorma", "CurrentBMI" + currentBMI);
        return currentBMI;

    }

    public String getCurrentUser() {
        return currentUser;
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

    public Date getDate() {
        return new Date();
    }

    public ArrayList<String> getUsers() {
        ArrayList<String> userList = new ArrayList<>();
        userList.add("Lisää käyttäjä");
        for(String user : usersHashMap.keySet()) {
            userList.add(user);
        }

        return userList;
    }

    public void setUsersHashMap(HashMap<String, ArrayList<Observation>> usersHashMap) {
        this.usersHashMap = usersHashMap;
    }

    public String appDataToGson() {
        json = gson.toJson(usersHashMap);

        return json;
    }

    public HashMap<String, ArrayList<Observation>> appDataFromGson(String json) {
        HashMap<String, ArrayList<Observation>> fromJson = gson.fromJson(json, token.getType());

        return fromJson;
    }


}
