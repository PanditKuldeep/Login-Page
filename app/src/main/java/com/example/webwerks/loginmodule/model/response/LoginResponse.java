package com.example.webwerks.loginmodule.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponse{


    @SerializedName("user_msg")
    public String user_msg;

    @SerializedName("data")
    public Data data;


    //object
    public class Data {

        @SerializedName("id")
        public String id;

        @SerializedName("role_id")
        public String role_id;

        @SerializedName("first_name")
        public String first_name;

        @SerializedName("last_name")
        public String last_name;

        @SerializedName("email")
        public String email;

        @SerializedName("username")
        public String username;

        @SerializedName("profile_pic")
        public String profile_pic;

        @SerializedName("country_id")
        public String country_id;

        @SerializedName("gender")
        public String gender;

        @SerializedName("phone_no")
        public String phone_no;

        @SerializedName("dob")
        public String dob;

        @SerializedName("is_active")
        public String is_active;

        @SerializedName("created")
        public String created;

        @SerializedName("modified")
        public String modified;

        @SerializedName("access_token")
        public String access_token;

    }
}
