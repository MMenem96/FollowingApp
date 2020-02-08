
package com.domain.aly.followingapp.followingutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowingResponse implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
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
    @SerializedName("followed")
    @Expose
    private Followed followed;
    private final static long serialVersionUID = 7103866887830587182L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FollowingResponse() {
    }

    /**
     * 
     * @param id
     * @param followedName
     * @param followedPhone
     * @param follower
     * @param followerName
     * @param followed
     */
    public FollowingResponse(int id, String followedName, long follower, String followerName, long followedPhone, Followed followed) {
        super();
        this.id = id;
        this.followedName = followedName;
        this.follower = follower;
        this.followerName = followerName;
        this.followedPhone = followedPhone;
        this.followed = followed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Followed getFollowed() {
        return followed;
    }

    public void setFollowed(Followed followed) {
        this.followed = followed;
    }

}
