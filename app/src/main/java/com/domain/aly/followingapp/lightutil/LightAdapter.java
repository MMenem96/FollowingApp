package com.domain.aly.followingapp.lightutil;


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

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.MyViewHolder> {

    private List<LightResponse> lightResponseList;

    private Context context;

    public LightAdapter(List<LightResponse> lightResponseList, Context context) {
        this.lightResponseList = lightResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        LightResponse lightResponse = lightResponseList.get(position);

        String lightDate = lightResponse.getDate();
        String lightValue = String.valueOf(lightResponse.getValue());
        holder.lightDate.setText("Date: " + lightDate);
        holder.lightValue.setText("Value: " + lightValue);

    }

    @Override
    public int getItemCount() {
        return lightResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lightDate, lightValue;
        public ImageView ivLight;

        public MyViewHolder(View view) {
            super(view);
            lightDate = (TextView) view.findViewById(R.id.lightDate);
            lightValue = (TextView) view.findViewById(R.id.lightValue);
            ivLight = (ImageView) view.findViewById(R.id.thumbnailLights);

        }
    }

}
