package com.domain.aly.followingapp.apputil;

public class UrlConstants {

    public static final String BASE_URL = "http://192.168.191.4:8080/";

    //Get user by phone
    public static final String USER_BY_PHONE = "admin/admins/{phone}";

    //Add new user
    public static final String CREATE_A_NEW_USER = "admin/admins";


    //Get Records.

    //Get All light reports by user phone.
    public static final String LIGHT_RECORDS_BY_PHONE = "light/{phone}";

    //Get All noise reports by user phone.
    public static final String Noise_RECORDS_BY_PHONE = "noise/{phone}";

    //Get All Accelerometer reports by user phone.
    public static final String ACCELEROMETER_RECORDS_BY_PHONE = "accelerometer/{phone}";

    //Get All Accelerometer reports by user phone.
    public static final String SPEED_RECORDS_BY_PHONE = "speed/{phone}";

    //Get All Locations reports by user phone.
    public static final String LOCATION_RECORDS_BY_PHONE = "location/{phone}";

    //Get All Followers  by user phone.
    public static final String FOLLOWERS_BY_PHONE = "following/getfollowers/{phone}";

    //Get All Following by user phone.
    public static final String FOLLOWING_BY_PHONE = "following/getfollowed/{phone}";


    //Delete  Followers  by user phone.
    public static final String DELETE_FOLLOWERS_BY_PHONE = "following/delete/{id}";


    //Post Records.


    //Post Accelerometer records.
    public static final String POST_ACCELEROMETER_RECORDS_BY_PHONE = "accelerometer/{phone}";

    //Post Light records.
    public static final String POST_LIGHT_RECORDS_BY_PHONE = "light/{phone}";

    //Post Noise records.
    public static final String POST_NOISE_RECORDS_BY_PHONE = "noise/{phone}";

    //Post Speed records.
    public static final String POST_SPEED_RECORDS_BY_PHONE = "speed/{phone}";

    //Post All Locations reports.
    public static final String POST_LOCATION_RECORDS_BY_PHONE = "location/{phone}";

    //Accept following request.
    public static final String POST_FOLLOWING_REQUEST = "following/post/{phone)";
}
