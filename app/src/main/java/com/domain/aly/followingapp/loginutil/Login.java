
package com.domain.aly.followingapp.loginutil;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private int phone;
    private final static long serialVersionUID = 6301500637559035788L;

    /**
     * No args constructor for use in serialization
     */
    public Login() {
    }

    /**
     * @param phone
     * @param name
     */
    public Login(String name, int phone) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}