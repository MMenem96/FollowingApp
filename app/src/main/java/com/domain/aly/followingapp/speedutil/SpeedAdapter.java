package com.domain.aly.followingapp.speedutil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.domain.aly.followingapp.R;

import java.util.List;

class SpeedAdapter extends RecyclerView.Adapter<SpeedAdapter.MyViewHolder> {

    private List<SpeedResponse> speedResponseList;

    private Context context;

    public SpeedAdapter(List<SpeedResponse> speedResponseList, Context context) {
        this.speedResponseList = speedResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.speed_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SpeedResponse speedResponse = speedResponseList.get(position);

        String speedDate = speedResponse.getDate();
        String speeedValue = String.valueOf(speedResponse.getValue());
        holder.speedDate.setText("Date: " + speedDate);
        holder.speedValue.setText("Value: " + speeedValue);

    }

    @Override
    public int getItemCount() {
        return speedResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView speedDate, speedValue;
        public ImageView ivSpeed;

        public MyViewHolder(View view) {
            super(view);
            speedDate = (TextView) view.findViewById(R.id.speedDate);
            speedValue = (TextView) view.findViewById(R.id.speedValue);
            ivSpeed = (ImageView) view.findViewById(R.id.thumbnailSpeeds);

        }
    }

}
