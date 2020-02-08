package com.domain.aly.followingapp.accelerometerutil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.noiseutil.NoiseResponse;

import java.util.List;

class AccelerometerAdapter extends RecyclerView.Adapter<AccelerometerAdapter.MyViewHolder> {

    private List<AccelerometerResponse> accelerometerResponseList;

    private Context context;

    public AccelerometerAdapter(List<AccelerometerResponse> accelerometerResponseList, Context context) {
        this.accelerometerResponseList = accelerometerResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.accelerometer_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        AccelerometerResponse accelerometerResponse = accelerometerResponseList.get(position);

        String accelerometerDate = accelerometerResponse.getDate();
        String accelerometerValue = String.valueOf(accelerometerResponse.getValue());
        holder.accelerometerDate.setText("Date: " + accelerometerDate);
        holder.accelerometerValue.setText("Value: " + accelerometerValue);

    }

    @Override
    public int getItemCount() {
        return accelerometerResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView accelerometerDate, accelerometerValue;
        public ImageView ivAccelerometer;

        public MyViewHolder(View view) {
            super(view);
            accelerometerDate = (TextView) view.findViewById(R.id.accelerometerDate);
            accelerometerValue = (TextView) view.findViewById(R.id.accelerometerValue);
            ivAccelerometer = (ImageView) view.findViewById(R.id.thumbnailAccelerometers);

        }
    }

}
