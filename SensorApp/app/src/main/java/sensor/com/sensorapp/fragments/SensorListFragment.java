package sensor.com.sensorapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sensor.com.sensorapp.R;
import sensor.com.sensorapp.adapters.SensorListAdapter;
import sensor.com.sensorapp.models.Resource;

public class SensorListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private static ArrayList<Resource> mSensorsList;

    public static SensorListFragment newInstance(Context context, ArrayList<Resource> users) {
        SensorListFragment sensorListFragment = new SensorListFragment();
        mSensorsList = users;
        return sensorListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setAdapter(new SensorListAdapter(getActivity(), mSensorsList));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public RecyclerView.LayoutManager getLinearLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        return layoutManager;
    }
}
