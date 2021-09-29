package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

public class HeightObservation extends Observation {
    private int height;
    private Date date;

    public HeightObservation(int height, Date date) {
        this.height = height;
        this.date = date;
    }
}
