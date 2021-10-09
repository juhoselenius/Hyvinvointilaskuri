package fi.mobts.hyvinvointilaskuri;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import fi.mobts.hyvinvointilaskuri.classes.Observation;
import fi.mobts.hyvinvointilaskuri.classes.ObservationDeserializer;

/**
 * The class <code>UserListGlobal</code> is used to create the singleton of the application.
 * It is used as a run-time data bank and also for saving the data, when the application is closed or interrupted.
 * @author Tommi Uponen, Olli Varila and Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class UserListGlobal {
    private static final UserListGlobal ourInstance = new UserListGlobal();
    private static final TypeToken token = new TypeToken<LinkedHashMap<String,ArrayList<Observation>>>(){};
    private LinkedHashMap<String, ArrayList<Observation>> usersHashMap;
    private String currentUser;
    private Gson gson;
    private String json;
    private ArrayList<String> userListSnapShot;

    public static UserListGlobal getInstance() {
        return ourInstance;
    }

    /**
     * Creates the <code>UserListGlobal</code> singleton.
     */
    private UserListGlobal() {
        usersHashMap = new LinkedHashMap<>();
        userListSnapShot = new ArrayList<>();
        gson = new GsonBuilder().registerTypeAdapter(Observation.class, new ObservationDeserializer()).create();
    }

    /**
     * The method sets the given user as the current user.
     * @param currentUser The current user that will be set.
     */

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        Log.d("Jorma", "Käyttäjä asetettu " + currentUser);
    }

    /**
     * The method fetches the most recent weight data.
     * @return most recent weight data.
     */

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

    /**
     * The method fetches all the weights of a specific user.
     * @param userName The user name, whose weights are to be fetched.
     * @return an arraylist of weights.
     */

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

    public double getMaxWeight() {
        ArrayList<Double> weights = getWeightsList(currentUser);
        double maxWeight = 0;
        for(double i : weights) {
            if(maxWeight< i) {
                maxWeight = i;
            }
        }

        return maxWeight;
    }

    public double getMinWeight() {
        ArrayList<Double> weights = getWeightsList(currentUser);
        double minWeight = 1000;
        for(double i : weights) {
            if(minWeight > i) {
                minWeight = i;
            }
        }

        return minWeight;
    }

    public double getMaxWeightDate() {
        ArrayList<Date> dates = getWeightDatesList(currentUser);
        Date maxDate = new Date(Long.MIN_VALUE);
        for(Date i : dates) {
            if(i.after(maxDate)) {
                maxDate = i;
            }
        }

        long tempLong = maxDate.getTime();
        double newMaxDate = (double) tempLong;

        return newMaxDate;
    }

    public double getMinWeightDate() {
        ArrayList<Date> dates = getWeightDatesList(currentUser);
        Date minDate = new Date(Long.MAX_VALUE);
        for(Date i : dates) {
            if(i.before(minDate)) {
                minDate = i;
            }
        }

        long tempLong = minDate.getTime();
        double newMinDate = (double) tempLong;

        return newMinDate;
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
        if (!userList.equals("Ei käyttäjää")) {
            userList.add(currentUser);
        }

        if(userListTemp.size() == 0) {
            userList.add("Ei käyttäjää");
        } else {
            for(int i = userListTemp.size()-1; i >= 0; i--) {
                userList.add(userListTemp.get(i));
            }
        }
        int removeIndex = 0;
        int count = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(currentUser)) {
                count++;
                removeIndex = i;
            }
        }
        if (count > 1) {
            userList.remove(removeIndex);
        }
        Log.d("Jorma", "käyttäjälista haettu");
        userListSnapShot = userList;
        return userList;
    }

    public ArrayList<String> getUserListSnapShot() {
        return userListSnapShot;
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
