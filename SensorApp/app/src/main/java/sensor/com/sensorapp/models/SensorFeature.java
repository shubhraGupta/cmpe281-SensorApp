package sensor.com.sensorapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class SensorFeature extends BaseModel implements Serializable {
    private String date;
    private String value;

    public String getDate(){
        return date;
    }

    public String getValue(){
        return value;
    }

    public static SensorFeature fromJson(JSONObject json) {
        SensorFeature sf = new SensorFeature();

        try {
            sf.jsonObject = json;
            sf.date = json.getString("date");
            sf.value = json.getString("value");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sf;
    }

    public static ArrayList<SensorFeature> fromJson(JSONArray jsonArray) {
        ArrayList<SensorFeature> features = new ArrayList<SensorFeature>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject featureJson = null;
            try {
                featureJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            SensorFeature feature = SensorFeature.fromJson(featureJson);
            if (feature != null) {
                features.add(feature);
            }
        }
        return features;
    }
}
