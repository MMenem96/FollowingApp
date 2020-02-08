
package com.domain.aly.followingapp.speedutil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class SpeedPostRequest implements Serializable {

    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("date")
    @Expose
    private Date date;
    private final static long serialVersionUID = 4259458351443508593L;

    /**
     * No args constructor for use in serialization
     */
    public SpeedPostRequest() {
    }

    /**
     * @param value
     * @param date
     */
    public SpeedPostRequest(Double value, Date date) {
        super();
        this.value = value;
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}