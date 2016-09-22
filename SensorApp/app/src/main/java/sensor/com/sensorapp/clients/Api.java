package sensor.com.sensorapp.clients;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;

public class Api {
    private static AsyncHttpClient aSyncClient;
    private static String USER_AGENT = "Our Custom User Agent";

    static {

        // setup asynchronous client
        aSyncClient = new AsyncHttpClient();
        aSyncClient.setUserAgent(USER_AGENT);
        aSyncClient.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
        aSyncClient.getHttpClient().getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        aSyncClient.getHttpClient().getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("DEBUG", "In get");
        aSyncClient.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        aSyncClient.post(url, params, responseHandler);
    }

    public static void put(String url, AsyncHttpResponseHandler responseHandler) {
        aSyncClient.put(null, url, null, "application/json", responseHandler);
    }

    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        aSyncClient.delete(null, url, responseHandler);
    }
}
