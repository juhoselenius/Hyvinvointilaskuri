package fi.mobts.hyvinvointilaskuri.classes;
import android.util.Log;

import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Date;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;

/**
 * The class <code>GraphViewFun</code> contains the functionality of the Graphview.
 * @author Tommi Uponen, Olli Varila and Juho Selenius
 * @version 1.0 (13.10.2021)
 */

public class GraphViewFun {
    private ArrayList<Date> weightDateList;
    private ArrayList<Double> weightList;

    /**
     * The method is used to create a new GraphViewFun object.
     */

    public GraphViewFun() {
        this.weightDateList = new ArrayList<>();
        this.weightList = new ArrayList<>();

    }

    /**
     * The method fetches weight observation values and their dates and convert them to a datapoint table.
     * @return A datapoint table of weight observations.
     */

    public DataPoint[] getweightDatapoints () {
        weightDateList = UserListGlobal.getInstance().getWeightDatesList(UserListGlobal.getInstance().getCurrentUser());
        weightList = UserListGlobal.getInstance().getWeightsList(UserListGlobal.getInstance().getCurrentUser());

        Log.d("Jorma", "Painolista:" +weightList);
        Log.d("Jorma", "Painojen päivämäärälista:" +weightDateList);

        DataPoint[] weightDataPoints = new DataPoint[weightList.size()];
        for(int i = 0; i < weightList.size(); i++) {
            weightDataPoints[i] = new DataPoint(weightDateList.get(i), weightList.get(i));
        }

        return weightDataPoints;
    }
}