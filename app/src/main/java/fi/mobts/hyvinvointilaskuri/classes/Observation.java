package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

/**
 * The class <code>Observation</code> is used to define the subclasses <code>HeightObservation</code> and <code>WeightObservation</code>.
 * @author Tommi Uponen, Olli Varila and Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public abstract class Observation {

    /**
     * The method for returning the type of the observation.
     * @return The type of the observation.
     */

    public abstract String getType();

    /**
     * The method for returning the weight value of the observation.
     * @return The weight value of the observation.
     */

    public abstract double getWeight();

    /**
     * The method for returning the date of the observation.
     * @return The date of the observation.
     */

    public abstract Date getDate();

    /**
     * The method for returning the height value of the observation.
     * @return The height value of the observation.
     */

    public abstract int getHeight();

}


