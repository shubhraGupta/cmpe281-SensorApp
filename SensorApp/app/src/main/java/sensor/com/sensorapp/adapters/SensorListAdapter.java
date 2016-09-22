package sensor.com.sensorapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sensor.com.sensorapp.R;
import sensor.com.sensorapp.models.Resource;

public class SensorListAdapter extends RecyclerView.Adapter<SensorListAdapter.SensorViewHolder>{

    private List<? extends Resource> mSensorList;
    private Context mContext;

    public SensorListAdapter(Context context, ArrayList<Resource> sensorList) {
        this.mContext = context;
        this.mSensorList = sensorList;
    }


    @Override
    public SensorListAdapter.SensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_item, parent, false);
        SensorViewHolder viewHolder = new SensorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SensorListAdapter.SensorViewHolder holder, int position) {
        final Resource item = mSensorList.get(position);
        holder.sensorName.setText(item.getSensorName());
        holder.sensorUsage.setText("Usage : " + item.getSensorUsage() + " hrs ");
        holder.sensorLocation.setText(item.getSensorLocation());
        holder.sensorAmount.setText("$" + item.getSensorAmount());
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, item.getSensorName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSensorList.size();
    }

    public static class SensorViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView sensorName, sensorUsage, sensorLocation, sensorAmount;
        protected RelativeLayout mContainer;

        public SensorViewHolder(View itemView) {
            super(itemView);
            this.mContainer = (RelativeLayout) itemView.findViewById(R.id.item_container);
            this.imageView = (ImageView) itemView.findViewById(R.id.user_profile);
            this.sensorName = (TextView) itemView.findViewById(R.id.sensor_name);
            this.sensorUsage = (TextView) itemView.findViewById(R.id.sensor_usage);
            this.sensorLocation = (TextView) itemView.findViewById(R.id.sensor_location);
            this.sensorAmount = (TextView) itemView.findViewById(R.id.sensor_amount);
        }
    }
}
