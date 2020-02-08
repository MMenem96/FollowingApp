
package com.domain.aly.followingapp.signuputil;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private Long phone;
    private final static long serialVersionUID = -5595047231220537509L;

    /**
     * No args constructor for use in serialization
     */
    public Signup() {
    }

    /**
     * @param phone
     * @param name
     */
    public Signup(String name, Long phone) {
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}