
package com.domain.aly.followingapp.lightutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LightResponse implements Serializable
{

    @SerializedName("idlight")
    @Expose
    private long idlight;
    @SerializedName("value")
    @Expose
    private long value;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("admin")
    @Expose
    private Admin admin;
    private final static long serialVersionUID = -4719092240045538169L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LightResponse() {
    }

    /**
     * 
     * @param admin
     * @param value
     * @param idlight
     * @param date
     */
    public LightResponse(long idlight, long value, String date, Admin admin) {
        super();
        this.idlight = idlight;
        this.value = value;
        this.date = date;
        this.admin = admin;
    }

    public long getIdlight() {
        return idlight;
    }

    public void setIdlight(long idlight) {
        this.idlight = idlight;
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
