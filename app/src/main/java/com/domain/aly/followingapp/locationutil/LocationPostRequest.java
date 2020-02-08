
package com.domain.aly.followingapp.locationutil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class LocationPostRequest implements Serializable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("date")
    @Expose
    private Date date;
    private final static long serialVersionUID = 4259458351443508593L;

    /**
     * No args constructor for use in serialization
     */
    public LocationPostRequest() {
    }

    /**
     * @param url
     * @param date
     */
    public LocationPostRequest(String url, Date date) {
        super();
        this.url = url;
        this.date = date;
    }

    public String getValue() {
        return url;
    }

    public void setValue(String value) {
        this.url = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}