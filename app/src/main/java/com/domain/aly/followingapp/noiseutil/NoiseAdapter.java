package com.domain.aly.followingapp.noiseutil;

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

class NoiseAdapter extends RecyclerView.Adapter<NoiseAdapter.MyViewHolder> {

    private List<NoiseResponse> noiseResponseList;

    private Context context;

    public NoiseAdapter(List<NoiseResponse> noiseResponseList, Context context) {
        this.noiseResponseList = noiseResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.noise_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        NoiseResponse noiseResponse = noiseResponseList.get(position);

        String noiseDate = noiseResponse.getDate();
        String noiseValue = String.valueOf(noiseResponse.getValue());
        holder.noiseDate.setText("Date: " + noiseDate);
        holder.noiseValue.setText("Value: " + noiseValue);

    }

    @Override
    public int getItemCount() {
        return noiseResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView noiseDate, noiseValue;
        public ImageView ivNoise;

        public MyViewHolder(View view) {
            super(view);
            noiseDate = (TextView) view.findViewById(R.id.noiseDate);
            noiseValue = (TextView) view.findViewById(R.id.noiseValue);
            ivNoise = (ImageView) view.findViewById(R.id.thumbnailNoises);

        }
    }

}
