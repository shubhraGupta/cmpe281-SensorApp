package sensor.com.sensorapp.clients;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class SensorClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/";
    private static SensorClient sInstance = null;

    public static SensorClient getInstance() {
        if (sInstance == null) {
            sInstance = new SensorClient();
        }

        return sInstance;
    }

    public String getDatabaseName() {
        return "locations";
    }

    public String getApiKey() {
        return "0sXtBAePTnK90KyLqgoy6psrdiSGT4s6";
    }

    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    public String docApiKeyUrl()
    {
        return "&apiKey="+getApiKey();
    }

    /*public String documentRequest()
    {
        return "sensorCloud";
    }*/

    public String documentRequest()
    {
        return "usersensors";
    }

    public String getQuery(String name){
        //return "?q={name%3A%22Conductivity2015%22}";
        return "?q={%22username%22:%22" + name + "%22}";
    }

    public void getUserDetails(String name, AsyncHttpResponseHandler handler){
        Log.d("DEBUG", "In get user details");
        Api.get(getBaseUrl()+documentRequest()+ getQuery(name) + docApiKeyUrl(), null, handler);
        //Api.get("https://api.mongolab.com/api/1/databases/locations/collections/usersensors?q={%22username%22:%22Sonika%22}&apiKey=0sXtBAePTnK90KyLqgoy6psrdiSGT4s6",null,handler);
    }
}
