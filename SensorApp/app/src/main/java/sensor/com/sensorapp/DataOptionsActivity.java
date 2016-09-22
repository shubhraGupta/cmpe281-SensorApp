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
import sensor.com.sensorapp.clients.DataClient;
import sensor.com.sensorapp.models.Resource;
import sensor.com.sensorapp.models.SensorData;

public class DataOptionsActivity extends AppCompatActivity implements Serializable {
    Button btncond;
    Button btnDen;
    Button btnTemp;
    Button btnSal;
    EditText ed1;
    ArrayList<Resource> resources;
    String nextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_options);

        btncond  = (Button) findViewById(R.id.buttoncond);
        btnSal = (Button) findViewById(R.id.buttonsali);
        btnTemp = (Button) findViewById(R.id.buttontemp);
        btnDen = (Button) findViewById(R.id.buttonden);

        resources = (ArrayList<Resource>) getIntent().getSerializableExtra("sensor_list");

        for(Resource r : resources){
            if(r.getType().equals("Conductivity"))
                btncond.setVisibility(View.VISIBLE);
            if(r.getType().equals("Temperature"))
                btnTemp.setVisibility(View.VISIBLE);
            if(r.getType().equals("Salinity"))
                btnSal.setVisibility(View.VISIBLE);
            if(r.getType().equals("Density"))
                btnDen.setVisibility(View.VISIBLE);
        }

        ed1=(EditText)findViewById(R.id.editText1);

        btncond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Conductivity");
            }
        });

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Temperature");
            }
        });

        btnSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Salinity");
            }
        });

        btnDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData("Density");
            }
        });


    }

    public void getData(String type){
        if (ed1.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Year", Toast.LENGTH_SHORT).show();
        } else {
            getSensorData(type + ed1.getText().toString(), type + " (" +  ed1.getText().toString() + ")");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    private void getSensorData(String name, String title) {
        //Log.d("DEBG","username :" + userName);
        nextTitle = title;
        DataClient.getInstance().getUserDetails(name, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonUsers) {
                super.onSuccess(statusCode, headers, jsonUsers);

                Log.d("DEBUG", "sensor data " + jsonUsers.toString());

                ArrayList<SensorData> data = SensorData.fromJson(jsonUsers);
                //ArrayList<Resource> resources = users.get(0).getResources();
                //Intent intent = new Intent(MainActivity.this, SensorListActivity.class);
                //intent.putExtra("sensor_list", resources);
                if(data.size()!=0) {
                    Intent intent = new Intent(DataOptionsActivity.this, ChartDisplayActivity.class);
                    intent.putExtra("data_list", data);
                    intent.putExtra("title", nextTitle);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "No Data to show", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", "error fetching user details " + statusCode + " " + responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
