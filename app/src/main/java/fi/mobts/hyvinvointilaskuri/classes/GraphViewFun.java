package fi.mobts.hyvinvointilaskuri.classes;
import android.util.Log;

import com.jjoe64.graphview.*;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Date;
import fi.mobts.hyvinvointilaskuri.UserListGlobal;

public class GraphViewFun {
    private ArrayList<Date> bmiDateList;
    private ArrayList<Date> weightDateList;
    private ArrayList<Double> weightList;
    private ArrayList<Double> bmiList;

    public GraphViewFun() {
        this.bmiDateList = new ArrayList<>();
        this.bmiList = new ArrayList<>();
        this.weightDateList = new ArrayList<>();
        this.weightList = new ArrayList<>();

    }

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