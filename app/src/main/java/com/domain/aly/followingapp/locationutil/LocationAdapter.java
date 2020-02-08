package com.domain.aly.followingapp.locationutil;

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

class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<LocationResponse> locationResponseList;

    private Context context;

    public LocationAdapter(List<LocationResponse> locationResponseList, Context context) {
        this.locationResponseList = locationResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        LocationResponse locationResponse = locationResponseList.get(position);

        String locationDate = locationResponse.getDate();
        String locationURL = String.valueOf(locationResponse.getUrl());
        holder.tvLocationDate.setText("Date: " + locationDate);
        holder.tvLocationValue.setText("Url: " + locationURL);

    }

    @Override
    public int getItemCount() {
        return locationResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLocationDate, tvLocationValue;
        public ImageView ivLocation;

        public MyViewHolder(View view) {
            super(view);
            tvLocationDate = (TextView) view.findViewById(R.id.locationDate);
            tvLocationValue = (TextView) view.findViewById(R.id.locationValue);
            ivLocation = (ImageView) view.findViewById(R.id.thumbnailLocations);

        }
    }

}
