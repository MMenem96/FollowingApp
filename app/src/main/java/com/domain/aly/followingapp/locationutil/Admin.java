
package com.domain.aly.followingapp.locationutil;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private long phone;
    private final static long serialVersionUID = -2117647168183811073L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Admin() {
    }

    /**
     * 
     * @param phone
     * @param name
     */
    public Admin(String name, long phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

}
