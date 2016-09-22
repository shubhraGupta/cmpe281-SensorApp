package sensor.com.sensorapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class SensorData extends BaseModel implements Serializable {
    private String name;
    private ArrayList<SensorFeature> features;

    public String getName(){
        return name;
    }

    public ArrayList<SensorFeature> getFeatures(){
        return features;
    }

    public static SensorData fromJson(JSONObject json) {
        SensorData s = new SensorData();

        try {
            s.jsonObject = json;
            s.name = json.getString("name");
            s.features = SensorFeature.fromJson((json.getJSONArray("features")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public static ArrayList<SensorData> fromJson(JSONArray jsonArray) {
        ArrayList<SensorData> sensors = new ArrayList<SensorData>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject sensorJson = null;
            try {
                sensorJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            SensorData sensor = SensorData.fromJson(sensorJson);
            if (sensor != null) {
                sensors.add(sensor);
            }
        }
        return sensors;
    }
}
