
package com.domain.aly.followingapp.accelerometerutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccelerometerResponse implements Serializable
{

    @SerializedName("idaccelerometer")
    @Expose
    private long idaccelerometer;
    @SerializedName("value")
    @Expose
    private double value;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("admin")
    @Expose
    private Admin admin;
    private final static long serialVersionUID = -1581342703144508829L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccelerometerResponse() {
    }

    /**
     * 
     * @param admin
     * @param value
     * @param date
     * @param idaccelerometer
     */
    public AccelerometerResponse(long idaccelerometer, double value, String date, Admin admin) {
        super();
        this.idaccelerometer = idaccelerometer;
        this.value = value;
        this.date = date;
        this.admin = admin;
    }

    public long getIdaccelerometer() {
        return idaccelerometer;
    }

    public void setIdaccelerometer(long idaccelerometer) {
        this.idaccelerometer = idaccelerometer;
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
