package fi.mobts.hyvinvointilaskuri.classes;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
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
}