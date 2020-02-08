
package com.domain.aly.followingapp.notificationutil;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowingRequest implements Serializable {


    @SerializedName("followedName")
    @Expose
    private String followedName;
    @SerializedName("followedPhone")
    @Expose
    private String followedPhone;
    private final static long serialVersionUID = 1437926168043165193L;

    /**
     * No args constructor for use in serialization
     */
    public FollowingRequest() {
    }

    /**
     * @param followedName
     * @param followedPhone
     */
    public FollowingRequest(String followedName, String followedPhone) {
        super();
        this.followedName = followedName;
        this.followedPhone = followedPhone;
    }

    public String getFollowedName() {
        return followedName;
    }

    public void setFollowedName(String followedName) {
        this.followedName = followedName;
    }

    public String getFollowedPhone() {
        return followedPhone;
    }

    public void setFollowedPhone(String followedPhone) {
        this.followedPhone = followedPhone;
    }


}