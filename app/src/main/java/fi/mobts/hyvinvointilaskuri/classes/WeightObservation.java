package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

public class WeightObservation extends Observation {
    private double weight;
    private Date date;
    private String type;

    public WeightObservation(Double weight, Date date) {
        this.weight = weight;
        this.date = date;
        this.type = "weight";
    }

    public int getHeight() {
        return 0;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public Date getDate() {
        return date;
    }
}
