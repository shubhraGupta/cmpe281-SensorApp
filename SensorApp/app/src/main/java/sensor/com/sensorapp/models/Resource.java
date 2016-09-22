package sensor.com.sensorapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Resource extends BaseModel implements Serializable {
    private String sensorName;
    private String sensorUsage;
    private String sensorLocation;
    private String sensorAmount;
    private String type;
    private int numOfResources;

    public String getSensorName() {
        return sensorName;
    }

    public String getSensorUsage() {
        return sensorUsage;
    }

    public String getSensorLocation(){
        return sensorLocation;
    }

    public String getSensorAmount(){
        return sensorAmount;
    }

    public String getType(){
        return type;
    }

    public int getNumOfResources() {
        return numOfResources;
    }

    public void setSensorName(String sensorname){
        this.sensorName = sensorname;
    }

    public void setUsage(String usage){
        this.sensorUsage = usage;
    }

    public void setSensorAmount(String amount){
        this.sensorAmount = amount;
    }

    public void setSensorLocation(String location){
        this.sensorLocation = location;
    }

    public void setType(String type){
        this.type = type;
    }

    public static Resource fromJson(JSONObject json) {
        Resource r = new Resource();

        try {
            r.jsonObject = json;
            r.sensorName = json.getString("sensor_name");
            r.sensorUsage = json.getString("sensor_usage");
            r.sensorLocation = json.getString("sensor_location");
            r.sensorAmount = json.getString("sensor_amount");
            r.numOfResources = json.length();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    public static ArrayList<Resource> fromJson(JSONArray jsonArray) {
        ArrayList<Resource> resources = new ArrayList<Resource>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject resJson = null;
            try {
                resJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Resource resource = Resource.fromJson(resJson);
            if (resource != null) {
                resources.add(resource);
            }
        }
        return resources;
    }
}
