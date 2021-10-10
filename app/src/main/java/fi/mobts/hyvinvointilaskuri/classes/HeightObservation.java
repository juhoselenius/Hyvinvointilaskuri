package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

/**
 * The class <code>HeightObservation</code> defines observations of the height type.
 * @author
 * @version 1.0 (13.10.2021)
 */

public class HeightObservation extends Observation {
    private int height;
    private Date date;
    private String type;

    /**
     * The constructor method creates new <code>HeightObservation</code>.
     * @param height The observed height value.
     * @param date The date of the observed height value.
     */

    public HeightObservation(int height, Date date) {
        this.height = height;
        this.date = date;
        this.type = "height";
    }

    /**
     * The method is never accessed in this type of observation.
     * @return Return value is 0.
     */

    public double getWeight() {
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
     * The method returns the height value of the observation.
     * @return The height value of the observation.
     */

    public int getHeight() {
        return height;
    }

    /**
     * The method returns the date of the height observation.
     * @return The date of the height observation.
     */

    @Override
    public Date getDate() {
        return date;
    }
}
