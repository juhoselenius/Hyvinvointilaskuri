package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

/**
 * The class <code>WeightObservation</code> defines observations of the weight type.
 * @author Tommi Uponen, Olli Varila and Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class WeightObservation extends Observation {
    private double weight;
    private Date date;
    private String type;

    /**
     * The constructor method creates new <code>WeightObservation</code>.
     * @param weight The observed weight value.
     * @param date The date of the observed weight value.
     */

    public WeightObservation(Double weight, Date date) {
        this.weight = weight;
        this.date = date;
        this.type = "weight";
    }

    /**
     * The method is never accessed in this type of observation.
     * @return Return value is 0.
     */

    public int getHeight() {
        return 0;
    }

    /**
     * The method returns the type of the observation.
     * @return The type of the observation.
     */

    public String getType() {
        return type;
    }

    /**
     * The method returns the weight value of the observation.
     * @return The weight value of the observation.
     */

    public double getWeight() {
        return weight;
    }

    /**
     * The method returns the date of the weight observation.
     * @return The date of the weight observation.
     */

    public Date getDate() {
        return date;
    }
}
