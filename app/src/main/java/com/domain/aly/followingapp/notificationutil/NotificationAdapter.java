package com.domain.aly.followingapp.notificationutil;

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
import com.domain.aly.followingapp.followingutil.FollowingReportsActivity;
import com.domain.aly.followingapp.followingutil.FollowingResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.FollowingViewHolder> {

    private final ApiManager apiManager;
    private List<FollowingRequest> followingRequestList;

    private Context context;
    private String clickedFollowingName;
    private Long clickedFollowingPhone;

    public NotificationAdapter(List<FollowingRequest> followingRequestList, Context context) {
        this.followingRequestList = followingRequestList;
        this.context = context;
        apiManager = new ApiManager(context);
    }


    @Override
    public FollowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_request_row, parent, false);
        return new FollowingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FollowingViewHolder holder, final int position) {

        FollowingRequest followingRequest = followingRequestList.get(position);

        String followingName = followingRequest.getFollowedName();
        String followingPhone = String.valueOf(followingRequest.getFollowedPhone());
        Toast.makeText(context, "" + followingName, Toast.LENGTH_SHORT).show();
//        holder.tvFollowingName.setText("Name: " + followingName);
//        holder.tvFollowingPhone.setText("Phone: " + followingPhone);
    }

//    private void openFollowingReportsActivity() {
//        Intent i = new Intent(context, FollowingReportsActivity.class);
//        i.putExtra(AppConst.FOLLOWING_NAME, clickedFollowingName);
//        i.putExtra(AppConst.FOLLOWING_PHONE, clickedFollowingPhone);
//        context.startActivity(i);
//    }
//
//    private void deleteUser(final int userId, final int position) {
//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case DialogInterface.BUTTON_POSITIVE:
//                        //Yes button clicked
//                        deleteFollowing(userId, position);
//                        break;
//
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        //No button clicked
//                        break;
//                }
//            }
//        };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
//                .setNegativeButton("No", dialogClickListener).show();
//    }

//    public void deleteFollowing(int id, final int position) {
//        apiManager.showDialog(context.getString(R.string.delete_txt));
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseBody> call = apiInterface.deleteFollowerResponse(id);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    apiManager.closeDialog();
//                    Toast.makeText(context, context.getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();
//                    followingResponseList.remove(position);
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position, followingResponseList.size());
//                } else {
//                    apiManager.closeDialog();
//                    Toast.makeText(context, context.getString(R.string.deletefailed_txt) + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                apiManager.closeDialog();
//                Toast.makeText(context, context.getString(R.string.network_txt), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }


    @Override
    public int getItemCount() {
        return followingRequestList.size();
    }


    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView btnDelete, btnAccept;


        public FollowingViewHolder(View view) {
            super(view);
            btnAccept = (TextView) mView.findViewById(R.id.btnAccept);
            btnDelete = (TextView) mView.findViewById(R.id.btnDelete);


        }
    }

}
