package sensor.com.sensorapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import sensor.com.sensorapp.fragments.SensorListFragment;
import sensor.com.sensorapp.models.Resource;

public class SensorListActivity extends AppCompatActivity {
    private static final String SENSOR_FRAGMENT = "sensor_list_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor_list);

        if (savedInstanceState == null)
            initFragments();
    }

    private void initFragments() {

        ArrayList<Resource> resources = (ArrayList<Resource>) getIntent().getSerializableExtra("sensor_list");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, SensorListFragment.newInstance(this, resources), SENSOR_FRAGMENT).commit();

    }
}
