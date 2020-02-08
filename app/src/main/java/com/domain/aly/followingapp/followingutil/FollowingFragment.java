package com.domain.aly.followingapp.followingutil;

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
import com.domain.aly.followingapp.followersutil.FollowersResponse;
import com.domain.aly.followingapp.loginutil.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class FollowingFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView followingRecyclerView;
    private Long userPhone;
    private List<FollowingResponse> followingResponse;
    private FollowingAdapter followingAdapter;

    public FollowingFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = ApiClient.getClient();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.following_fragment, container, false);
        UserCachedInfo userCachedInfo = new UserCachedInfo(getActivity());
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {
            userPhone = userCachedInfo.getPhone();
            followingRecyclerView = (RecyclerView) view.findViewById(R.id.rvFollowing);
            followingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            getFollowingRequest(userCachedInfo.getPhone());
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

    private void getFollowingRequest(Long phone) {
        // showDialog(context.getString(R.string.getting_light_txt));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<FollowingResponse>> call = apiService.getFollowingResponse(phone);
        call.enqueue(new Callback<List<FollowingResponse>>() {


            @Override
            public void onResponse(Call<List<FollowingResponse>> call, retrofit2.Response<List<FollowingResponse>> response) {
                // closeDialog();
                if (response.isSuccessful()) {
                    followingResponse = response.body();
                    if (followingResponse != null) {
                        followingAdapter = new FollowingAdapter(followingResponse, getContext());
                        followingRecyclerView.setAdapter(followingAdapter);

                    }
                } else {
                    Toast.makeText(getContext(), getActivity().getString(R.string.no_data_txt), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<FollowingResponse>> call, Throwable t) {
                //  closeDialog();
                //   Toast.makeText(getContext(), getActivity().getString(R.string.network_txt), Toast.LENGTH_LONG).show();
            }
        });
    }


}
