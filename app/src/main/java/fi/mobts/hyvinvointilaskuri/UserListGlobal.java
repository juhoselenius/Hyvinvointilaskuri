package fi.mobts.hyvinvointilaskuri;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.ObservationDeserializer;

public class UserListGlobal {
    private static final UserListGlobal ourInstance = new UserListGlobal();
    private static final TypeToken token = new TypeToken<LinkedHashMap<String,ArrayList<Observation>>>(){};
    private LinkedHashMap<String, ArrayList<Observation>> usersHashMap;
    private String currentUser;
    private Gson gson;
    private String json;

    public static UserListGlobal getInstance() {
        return ourInstance;
    }

    private UserListGlobal() {
        usersHashMap = new LinkedHashMap<>();
        gson = new GsonBuilder().registerTypeAdapter(Observation.class, new ObservationDeserializer()).create();
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        Log.d("Jorma", "Käyttäjä asetettu " + currentUser);
    }

    public double getCurrentWeight() {
        ArrayList<Observation> observations = usersHashMap.get(currentUser);
        double currentWeight = 1.0;
        if (!(getUsers().contains("Ei käyttäjää"))) {
            for (int i = observations.size() - 1; i >= 0; i--) {
                if (observations.get(i).getType().equals("weight")) {
                    currentWeight = observations.get(i).getWeight();
                    break;
                }
            }
        }
        return currentWeight;
    }

    public ArrayList<Double> getWeightsList(String userName) {
        ArrayList<Observation> observations = usersHashMap.get(userName);
        Log.d("Jorma", "Painolistan pituus: "+ observations.size());
        ArrayList<Double> weightList = new ArrayList<>();
        for(int i = 0; i < observations.size(); i++) {
            if(observations.get(i).getType().equals("weight")) {
                weightList.add(observations.get(i).getWeight());
            }
        }
        return weightList;
    }

    public ArrayList<Date> getWeightDatesList(String userName) {
        ArrayList<Observation> observations = usersHashMap.get(userName);
        Log.d("Jorma", "Päivämäärälistan pituus: "+ observations.size());
        ArrayList<Date> weightDateList = new ArrayList<>();
        for(int i = 0; i < observations.size(); i++) {
            if(observations.get(i).getType().equals("weight")) {
                weightDateList.add(observations.get(i).getDate());
            }
        }
        return weightDateList;
    }

    public double getCurrentHeight() {
        ArrayList<Observation> observations = usersHashMap.get(currentUser);
        int currentHeight = 0;
        if (!(getUsers().contains("Ei käyttäjää"))) {
            for (int i = observations.size() - 1; i >= 0; i--) {
                if (observations.get(i).getType().equals("height")) {
                    currentHeight = observations.get(i).getHeight();
                    break;
                }
            }
        }
        return currentHeight;
    }

    public double currentBMI() {
        double currentHeight = getCurrentHeight() / 100;
        double currentBMI = getCurrentWeight() / (currentHeight * currentHeight);
        return currentBMI;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void addObservation(String name, Observation observation) {
        usersHashMap.get(name).add(observation);
    }

    public void newUser(String name) {
        if (!usersHashMap.containsKey(name)) {
            usersHashMap.put(name, new ArrayList<>());
            Log.d("Jorma", "Hashmappiin lisätty " + name);
            Log.d("Jorma", "Hasmapissa nyt avaimet: "+ usersHashMap.keySet());
        }
    }

    public Date getDate() {
        return new Date();
    }

    public ArrayList<String> getUsers() {
        ArrayList<String> userList = new ArrayList<>();

        for(String user : usersHashMap.keySet()) {
            userList.add(user);
        }
        if (userList.size() ==  0){
            userList.add("Ei käyttäjää");
        }
        return userList;
    }

    public ArrayList<String> getDropdownUsers() {
        ArrayList<String> userListTemp = new ArrayList<>(usersHashMap.keySet());
        ArrayList<String> userList = new ArrayList<>();

        for(int i = userListTemp.size()-1; i >= 0; i--) {
            userList.add(userListTemp.get(i));
        }
        if (userList.size() ==  0){
            userList.add("Ei käyttäjää");
        }
        return userList;
    }

    public void setUsersHashMap(LinkedHashMap<String, ArrayList<Observation>> usersHashMap) {
        this.usersHashMap = usersHashMap;
    }

    public String appDataToGson() {
        json = gson.toJson(usersHashMap);

        return json;
    }

    public LinkedHashMap<String, ArrayList<Observation>> appDataFromGson(String json) {
        LinkedHashMap<String, ArrayList<Observation>> fromJson = gson.fromJson(json, token.getType());

        return fromJson;
    }

    public void removeUser(String user) {
        usersHashMap.remove(user);
        if(getUsers().size() == 0) {
            currentUser = null;
        } else {
            setCurrentUser(getUsers().get(0));
        }

    }


}
