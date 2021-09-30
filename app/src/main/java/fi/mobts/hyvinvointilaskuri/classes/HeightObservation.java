package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

public class HeightObservation implements Observation {
    private int height;
    private Date date;
    private String type;

    public HeightObservation(int height, Date date) {
        this.height = height;
        this.date = date;
        this.type = "height";
    }

    public double getWeight() {
        return 0;
    }

    public String getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
