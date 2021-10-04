package fi.mobts.hyvinvointilaskuri.classes;

import java.util.Date;

public abstract class Observation {

    public abstract String getType();

    public abstract double getWeight();

    public abstract Date getDate();

    public abstract int getHeight();

}


