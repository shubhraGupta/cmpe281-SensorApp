package sensor.com.sensorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import sensor.com.sensorapp.clients.SensorClient;
import sensor.com.sensorapp.models.UsersSensors;

public class MainActivity extends AppCompatActivity implements Serializable {
    Button b1;
    EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button1);
        ed1=(EditText)findViewById(R.id.editText1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
                } else {
                    getUserDetails(ed1.getText().toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getUserDetails(String userName) {
        Log.d("DEBG","username :" + userName);
        SensorClient.getInstance().getUserDetails(userName, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonUsers) {
                super.onSuccess(statusCode, headers, jsonUsers);

                Log.d("DEBUG", "statusCode : " + statusCode + " ***" + jsonUsers.toString());

                //ArrayList<User> users = User.fromJson(jsonUsers);
                ArrayList<UsersSensors> users = UsersSensors.fromJson(jsonUsers);
                Log.d("DEBUG","users :" + users);
                //ArrayList<Resource> resources = users.get(0).getResources();
                //Intent intent = new Intent(MainActivity.this, SensorListActivity.class);
                //intent.putExtra("sensor_list", resources);
                if(users.size() != 0) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("user_list", users);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials or no resources to track", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", "error fetching user details " + statusCode + " " + responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
