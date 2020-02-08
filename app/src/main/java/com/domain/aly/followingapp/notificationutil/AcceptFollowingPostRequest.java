package com.domain.aly.followingapp.notificationutil;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptFollowingPostRequest implements Serializable {

    @SerializedName("followedName")
    @Expose
    private String followedName;
    @SerializedName("follower")
    @Expose
    private long follower;
    @SerializedName("followerName")
    @Expose
    private String followerName;
    @SerializedName("followed_Phone")
    @Expose
    private long followedPhone;
    private final static long serialVersionUID = -5182539903802675542L;

    /**
     * No args constructor for use in serialization
     */
    public AcceptFollowingPostRequest() {
    }

    /**
     * @param followedName
     * @param followedPhone
     * @param follower
     * @param followerName
     */
    public AcceptFollowingPostRequest(String followedName, long follower, String followerName, long followedPhone) {
        super();
        this.followedName = followedName;
        this.follower = follower;
        this.followerName = followerName;
        this.followedPhone = followedPhone;
    }

    public String getFollowedName() {
        return followedName;
    }

    public void setFollowedName(String followedName) {
        this.followedName = followedName;
    }

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public long getFollowedPhone() {
        return followedPhone;
    }

    public void setFollowedPhone(long followedPhone) {
        this.followedPhone = followedPhone;
    }

}