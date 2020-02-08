package com.domain.aly.followingapp.followersutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiInterface;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.loginutil.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class FollowersFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView followersRecyclerView;
    private Long userPhone;
    private List<FollowersResponse> followersResponse;
    private FollowersAdapter followersAdapter;

    public FollowersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = ApiClient.getClient();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.followers_fragment, container, false);
        UserCachedInfo userCachedInfo = new UserCachedInfo(getActivity());
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
            followersRecyclerView = (RecyclerView) view.findViewById(R.id.rvFollowers);
            followersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            getFollowersRequest(userCachedInfo.getPhone());
        } else {
            openLoginActivity();
        }

        return view;
    }

    private void openLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        if (getActivity() != null)
            getActivity().finish();
    }

    private void getFollowersRequest(Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<FollowersResponse>> call = apiService.getFollowersResponse(phone);
        call.enqueue(new Callback<List<FollowersResponse>>() {


            @Override
            public void onResponse(Call<List<FollowersResponse>> call, retrofit2.Response<List<FollowersResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    followersResponse = response.body();
                    if (followersResponse != null) {
                        followersAdapter = new FollowersAdapter(followersResponse, getContext());
                        followersRecyclerView.setAdapter(followersAdapter);

                    }
                } else {
                    Toast.makeText(getContext(), getActivity().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<FollowersResponse>> call, Throwable t) {
                //  closeDialog();
              //  Toast.makeText(getActivity(,"Network Error!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
