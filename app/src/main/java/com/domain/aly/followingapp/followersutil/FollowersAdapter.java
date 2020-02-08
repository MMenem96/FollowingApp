package com.domain.aly.followingapp.followersutil;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apiinterface.ApiManager;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {

    private List<FollowersResponse> followersResponseList;

    private Context context;
    private ApiManager apiManager;

    public FollowersAdapter(List<FollowersResponse> followersResponseList, Context context) {
        this.followersResponseList = followersResponseList;
        this.context = context;
        apiManager = new ApiManager(context);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        FollowersResponse followersResponse = followersResponseList.get(position);

        String followerName = followersResponse.getFollowerName();
        String followerPhone = String.valueOf(followersResponse.getFollower());
        holder.tvFollowerName.setText("Name: " + followerName);
        holder.tvFollowerPhone.setText("Phone: " + followerPhone);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowersResponse followersResponse = followersResponseList.get(position);
                int userId = followersResponse.getId();

                deleteUser(userId, position);

            }
        });

    }

    private void deleteUser(final int userId, final int position) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        deleteFollower(userId, position);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void deleteFollower(int userId, final int position) {
        apiManager.showDialog(context.getString(R.string.delete_txt));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.deleteFollowerResponse(userId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    apiManager.closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();
                    followersResponseList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, followersResponseList.size());
                } else {
                    apiManager.closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletefailed_txt) + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                apiManager.closeDialog();
                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return followersResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFollowerName, tvFollowerPhone;
        public ImageView ivLocation, ivDelete;


        public MyViewHolder(View view) {
            super(view);
            tvFollowerName = (TextView) view.findViewById(R.id.follower_user_name);
            tvFollowerPhone = (TextView) view.findViewById(R.id.follower_user_phone);
            ivLocation = (ImageView) view.findViewById(R.id.follower_user_image);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);


        }
    }

}
