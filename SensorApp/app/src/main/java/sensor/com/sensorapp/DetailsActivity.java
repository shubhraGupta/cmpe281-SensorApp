package sensor.com.sensorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sensor.com.sensorapp.models.Resource;
import sensor.com.sensorapp.models.UsersSensors;


public class DetailsActivity extends Activity{
    TextView userProfileName;
    //TextView summary1;
    Button button1;
    Button payBtn;
    Button dataBtn;
    ArrayList<UsersSensors> users;
    ArrayList<Resource> resources = new ArrayList<Resource>();
    Map<String,Boolean> map = new HashMap<String,Boolean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d("DEBUG", "DetailsActivity created");

        //TextView orderButton = (TextView) findViewById(R.id.text_id);


        //users= (ArrayList<User>) getIntent().getSerializableExtra("user_list");
        users= (ArrayList<UsersSensors>) getIntent().getSerializableExtra("user_list");
        userProfileName = (TextView) findViewById(R.id.user_profile_name);
        button1 = (Button) findViewById(R.id.button1);
        payBtn = (Button) findViewById(R.id.button_pay);
        dataBtn = (Button)findViewById(R.id.button_data);
        userProfileName.setText(users.get(0).getUserName());
        //button1.setText("Resources in use : " + String.valueOf(users.get(0).getResources().size()));

        for(UsersSensors u: users){
            Resource r = new Resource();
            r.setSensorName(u.getSensorName());
            r.setSensorAmount(u.getAmount());
            r.setSensorLocation(u.getLocation());
            r.setUsage(u.getUsage());
            r.setType(u.getType());
            resources.add(r);
        }


        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(DetailsActivity.this, SensorListActivity.class);
                intent.putExtra("sensor_list", resources);
                startActivity(intent);
            }

        });

        payBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, PayActivity.class);
                startActivity(i);
            }
        });

        dataBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(DetailsActivity.this,DataOptionsActivity.class);
                i.putExtra("sensor_list", resources);
                startActivity(i);
            }
        });

    }


}