package fi.mobts.hyvinvointilaskuri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import fi.mobts.hyvinvointilaskuri.classes.User;

public class WeightDataGlobal {
    private List<Double> weights;
    private List<Date> time;
    private List<User> adder;
    private static final WeightDataGlobal ourInstance = new WeightDataGlobal();

    public static WeightDataGlobal getInstance() {
        return ourInstance;
    }

    private WeightDataGlobal() {
        weights = new ArrayList<>();
        time = new ArrayList<>();
    }

    public List<Double> getWeights() {
        return weights;
    }

    public List<Date> getTimes() {
        return time;
    }

    public void addWeight(double weight) {
        Date nyt = new Date();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String formatDate = formatter.format(nyt);

        weights.add(weight);
        time.add(nyt);
    }
}
