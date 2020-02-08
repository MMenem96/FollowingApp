
package com.domain.aly.followingapp.noiseutil;

import com.domain.aly.followingapp.lightutil.Admin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NoiseResponse implements Serializable
{

    @SerializedName("idnoise")
    @Expose
    private long idnoise;
    @SerializedName("value")
    @Expose
    private double value;
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
    public NoiseResponse() {
    }

    /**
     *
     * @param admin
     * @param value
     * @param idnoise
     * @param date
     */
    public NoiseResponse(long idnoise, long value, String date, Admin admin) {
        super();
        this.idnoise = idnoise;
        this.value = value;
        this.date = date;
        this.admin = admin;
    }

    public long getIdnoise() {
        return idnoise;
    }

    public void setIdnoise(long idnoise) {
        this.idnoise = idnoise;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
