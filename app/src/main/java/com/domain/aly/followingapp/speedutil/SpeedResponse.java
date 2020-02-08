
package com.domain.aly.followingapp.speedutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpeedResponse implements Serializable
{

    @SerializedName("idspeed")
    @Expose
    private long idspeed;
    @SerializedName("value")
    @Expose
    private long value;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("admin")
    @Expose
    private Admin admin;
    private final static long serialVersionUID = 4282573678663056037L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SpeedResponse() {
    }

    /**
     * 
     * @param idspeed
     * @param admin
     * @param value
     * @param date
     */
    public SpeedResponse(long idspeed, long value, String date, Admin admin) {
        super();
        this.idspeed = idspeed;
        this.value = value;
        this.date = date;
        this.admin = admin;
    }

    public long getIdspeed() {
        return idspeed;
    }

    public void setIdspeed(long idspeed) {
        this.idspeed = idspeed;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
