package com.domain.aly.followingapp.followingutil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apiinterface.ApiManager;
import com.domain.aly.followingapp.apputil.AppConst;
import com.domain.aly.followingapp.followersutil.FollowersResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.MyViewHolder> {

    private final ApiManager apiManager;
    private List<FollowingResponse> followingResponseList;

    private Context context;
    private String clickedFollowingName;
    private Long clickedFollowingPhone;

    public FollowingAdapter(List<FollowingResponse> followingResponseList, Context context) {
        this.followingResponseList = followingResponseList;
        this.context = context;
        apiManager = new ApiManager(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        FollowingResponse followingResponse = followingResponseList.get(position);

        String followingName = followingResponse.getFollowedName();
        String followingPhone = String.valueOf(followingResponse.getFollowedPhone());
        holder.tvFollowingName.setText("Name: " + followingName);
        holder.tvFollowingPhone.setText("Phone: " + followingPhone);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowingResponse followingResponse = followingResponseList.get(position);
                int userId = followingResponse.getId();
                deleteUser(userId, position);
            }
        });

        holder.rlFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowingResponse followingResponse = followingResponseList.get(position);
                int userId = followingResponse.getId();
                clickedFollowingName = followingResponse.getFollowedName();
                clickedFollowingPhone = followingResponse.getFollowedPhone();
                openFollowingReportsActivity();
            }
        });
    }

    private void openFollowingReportsActivity() {
        Intent i = new Intent(context, FollowingReportsActivity.class);
        i.putExtra(AppConst.FOLLOWING_NAME, clickedFollowingName);
        i.putExtra(AppConst.FOLLOWING_PHONE, clickedFollowingPhone);
        context.startActivity(i);
    }

    private void deleteUser(final int userId, final int position) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        deleteFollowing(userId, position);
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

    public void deleteFollowing(int id, final int position) {
        apiManager.showDialog(context.getString(R.string.delete_txt));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.deleteFollowerResponse(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    apiManager.closeDialog();
                    Toast.makeText(context, context.getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();
                    followingResponseList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, followingResponseList.size());
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
        return followingResponseList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFollowingName, tvFollowingPhone;
        public ImageView ivUserImage, ivDelete;
        public RelativeLayout rlFollowing;


        public MyViewHolder(View view) {
            super(view);
            tvFollowingName = (TextView) view.findViewById(R.id.following_user_name);
            tvFollowingPhone = (TextView) view.findViewById(R.id.following_user_phone);
            ivUserImage = (ImageView) view.findViewById(R.id.following_user_image);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            rlFollowing = (RelativeLayout) view.findViewById(R.id.rlfollowing);


        }
    }

}
