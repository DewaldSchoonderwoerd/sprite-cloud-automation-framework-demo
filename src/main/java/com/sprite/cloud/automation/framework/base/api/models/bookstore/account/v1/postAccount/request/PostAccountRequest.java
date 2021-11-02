package com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.request;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostAccountRequest {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostAccountRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}