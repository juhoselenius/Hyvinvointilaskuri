package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

public class WeightObservation extends Observation{
    private double weight;
    private Date date;

    public WeightObservation(Double weight, Date date) {
        this.weight = weight;
        this.date = date;
    }
}
