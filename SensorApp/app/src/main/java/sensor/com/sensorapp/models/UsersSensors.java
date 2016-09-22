package sensor.com.sensorapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersSensors extends BaseModel implements Serializable {

    private String username;
    private String sensorname;
    private String sensortype;
    private String location;
    private String usage;
    private String amount;

    public String getUserName() {
        return username;
    }

    public String getSensorName() {
        return sensorname;
    }

    public String getLocation(){
        return location;
    }

    public String getType(){
        return sensortype;
    }

    public String getUsage(){
        return usage;
    }

    public String getAmount(){
        return amount;
    }



    public static UsersSensors fromJson(JSONObject json) {
        UsersSensors u = new UsersSensors();

        try {
            u.jsonObject = json;
            u.username = json.getString("username");
            u.sensorname = json.getString("sensorname");
            u.location = json.getString("location");
            u.sensortype = json.getString("sensortype");
            u.amount = json.getString("amount");
            u.usage = json.getString("usage");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    public static ArrayList<UsersSensors> fromJson(JSONArray jsonArray) {
        ArrayList<UsersSensors> users = new ArrayList<UsersSensors>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson = null;
            try {
                userJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            UsersSensors user = UsersSensors.fromJson(userJson);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }
}
