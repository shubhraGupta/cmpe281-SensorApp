package sensor.com.sensorapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel implements Serializable {

    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String address;
    private int numOfResources;
    private ArrayList<Resource> resources;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public int getNumOfResources(){
        return numOfResources;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Resource> getResources(){
        return resources;
    }

    public static User fromJson(JSONObject json) {
        User u = new User();

        try {
            u.jsonObject = json;
            u.firstName = json.getString("first_name");
            u.lastName = json.getString("last_name");
            u.phoneNumber = json.getLong("phone_number");
            u.address = json.getString("address");
            u.numOfResources = json.getJSONArray("resources").length();
            u.resources = Resource.fromJson(json.getJSONArray("resources"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    public static ArrayList<User> fromJson(JSONArray jsonArray) {
        ArrayList<User> users = new ArrayList<User>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson = null;
            try {
                userJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            User user = User.fromJson(userJson);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }
}
