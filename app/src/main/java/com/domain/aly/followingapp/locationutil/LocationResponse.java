
package com.domain.aly.followingapp.locationutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponse implements Serializable
{

    @SerializedName("idlocation")
    @Expose
    private long idlocation;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("admin")
    @Expose
    private Admin admin;
    private final static long serialVersionUID = 8431390602605141374L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LocationResponse() {
    }

    /**
     * 
     * @param admin
     * @param idlocation
     * @param date
     * @param url
     */
    public LocationResponse(long idlocation, String url, String date, Admin admin) {
        super();
        this.idlocation = idlocation;
        this.url = url;
        this.date = date;
        this.admin = admin;
    }

    public long getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(long idlocation) {
        this.idlocation = idlocation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
