package sensor.com.sensorapp.clients;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by shubhra on 5/16/2016.
 */
public class DataClient {

    private static DataClient dInstance = null;

    public static DataClient getInstance() {
        if (dInstance == null) {
            dInstance = new DataClient();
        }

        return dInstance;
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

    public String getQuery(String name){
        //return "?q={name%3A%22Conductivity2015%22}";
        return "?q={name%3A%22" + name + "%22}";
    }

    public String docApiKeyUrl()
    {
        return "&apiKey="+getApiKey();
    }

    public String documentRequest()
    {
        return "sensorData";
    }



    public void getUserDetails(String name, AsyncHttpResponseHandler handler){
        Log.d("DEBUG", "In get user details");
        Api.get(getBaseUrl() + documentRequest()+ getQuery(name) + docApiKeyUrl(), null, handler);

    }
}
