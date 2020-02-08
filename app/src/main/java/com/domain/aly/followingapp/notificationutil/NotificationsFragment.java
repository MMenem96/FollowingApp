package com.domain.aly.followingapp.notificationutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apiinterface.ApiManager;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.loginutil.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private RecyclerView notificationList;
    private Query queryOnFollowingRequest;
    private DatabaseReference followingDatabaseReference;
    private String holla_key;
    private String notificationKey;
    private Long userPhone;
    private TextView tvName, btnDelete, btnAccept;
    private TextView tvUserName;
    private FollowingRequest followingRequest;
    private String followedUserName, userName;
    private Long followedPhone;
    private CardView rowCardView;
    private ApiManager apiManager;


    public NotificationsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserCachedInfo userCachedInfo = new UserCachedInfo(getActivity());
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
            userName = userCachedInfo.getName();
            apiManager = new ApiManager(getContext());
            Log.e("onCreateFragment", "entered");
            followingDatabaseReference = FirebaseDatabase.getInstance()
                    .getReference("followingRequest").child(String.valueOf(userPhone));
        } else {
            openLoginActivity();
        }

    }

    private void fetchNotifications() {
        Log.e("fetchNotifications", "enterd");

        followingDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("fetchNotification", "entered");
                followingRequest = dataSnapshot.getValue(FollowingRequest.class);

                try {
                    followedUserName = followingRequest.getFollowedName();
                    followedPhone = Long.valueOf(followingRequest.getFollowedPhone());
                    String fphone = followingRequest.getFollowedPhone();
                    if (followedUserName != null) {
                        tvUserName.setText("User " + followedUserName + " : " + fphone + " Wants you to follow him!");
                    } else {
                        rowCardView.setVisibility(View.GONE);
                    }
                } catch (NullPointerException e) {


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        FirebaseRecyclerOptions<FollowingRequest> options =
//                new FirebaseRecyclerOptions.Builder<FollowingRequest>()
//                        .setQuery(followingDatabaseReference, FollowingRequest.class)
//                        .build();
//
//        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<FollowingRequest, FollowingViewHolder>(options) {
//            @Override
//            public FollowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_request_row, parent, false);
//                return new FollowingViewHolder(itemView);
//            }
//
//            @Override
//            protected void onBindViewHolder(FollowingViewHolder holder, int position, FollowingRequest model) {
//
//                Log.e("onBindViewHolder", "enterd");
//                notificationKey = getRef(position).getKey();
//                String username = model.getFollowedName();
//                Toast.makeText(getContext(), "" + username, Toast.LENGTH_SHORT).show();
//                holder.btnAccept.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "Accept", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        };
//
//        notificationList.setAdapter(adapter);
    }


    private void openLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        //  notificationList = (RecyclerView) view.findViewById(R.id.notifications_list);
        rowCardView = (CardView) view.findViewById(R.id.cdFollowed);
        btnAccept = (TextView) view.findViewById(R.id.btnAccept);
        tvUserName = (TextView) view.findViewById(R.id.user_name);
        btnDelete = (TextView) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFollowingRequest();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (followedPhone != null) {
                    acceptFollowingRequest(followedPhone, new AcceptFollowingPostRequest(followedUserName, userPhone, userName, followedPhone));

                } else {
                    Toast.makeText(getContext(), "nukk", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fetchNotifications();
        Log.e("onCreateViewFragment", "entered");
        return view;
    }

    private void deleteFollowingRequest() {
        followingDatabaseReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(getContext(), getContext().getString(R.string.deletedSuccefully_txt), Toast.LENGTH_SHORT).show();
                rowCardView.setVisibility(View.GONE);
            }
        });
    }

    public void acceptFollowingRequest(Long phone, AcceptFollowingPostRequest acceptFollowingPostRequest) {
        apiManager.showDialog("Adding to Following List");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.acceptFollowingPostRequest(phone, acceptFollowingPostRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    apiManager.closeDialog();
                    Toast.makeText(getContext(), "RequestAccepted!", Toast.LENGTH_SHORT).show();
                    deleteFollowingRequest();
                    //   openHomeActivity();
                } else {
                    apiManager.closeDialog();
                    Toast.makeText(getContext(), "failed to accept: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                apiManager.closeDialog();
                Toast.makeText(getContext(), getActivity().getString(R.string.network_txt), Toast.LENGTH_LONG).show();

            }
        });
    }
}
