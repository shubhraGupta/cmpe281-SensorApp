package sensor.com.sensorapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sensor.com.sensorapp.models.SensorData;
import sensor.com.sensorapp.models.SensorFeature;

public class ChartDisplayActivity extends ActionBarActivity {

    ArrayList<SensorData> sensorData;
    ArrayList<SensorFeature> features;
    Map<Integer,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charts_main);

        setTitle(getIntent().getStringExtra("title"));

        sensorData = (ArrayList<SensorData>) getIntent().getSerializableExtra("data_list");

        features = sensorData.get(0).getFeatures();
        setMap();
        Log.d("DEBUG", "features :" + features);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    public void setMap(){

        map = new HashMap<Integer,String>();
        map.put(1,"JAN");
        map.put(2,"FEB");
        map.put(3,"MAR");
        map.put(4,"APR");
        map.put(5,"MAY");
        map.put(6,"JUN");
        map.put(7,"JUL");
        map.put(8,"AUG");
        map.put(9,"SEP");
        map.put(10,"OCT");
        map.put(11,"NOV");
        map.put(12,"DEC");

    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        int i = 0;
        int j = 0;
        while(j < 12 && i < features.size()){
            float number = Float.parseFloat(features.get(i).getValue());
            BarEntry v1e1 = new BarEntry(number, j++); // Jan
            valueSet1.add(v1e1);
            i += 30;
        }

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        i = 15; j = 0;
        while(j < 12 && i < features.size()){
            float number = Float.parseFloat(features.get(i).getValue());
            BarEntry v2e1 = new BarEntry(number, j++); // Jan
            valueSet2.add(v2e1);
            i += 30;
        }
        Log.d("DEBUG","value 1 : " + valueSet1.size());
        Log.d("DEBUG", "value 2 : " + valueSet2.size());

        /*BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);*/

       /* ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);*/

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Month Start");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Month End");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        int i = 0; int j = 1;
        while(j <= 12 && i < features.size()){

            xAxis.add(map.get(j));
            j++;
            i += 30;
        }
        Log.d("DEBUG","x axis : " + xAxis.size());
        /*xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");*/
        return xAxis;
    }
}