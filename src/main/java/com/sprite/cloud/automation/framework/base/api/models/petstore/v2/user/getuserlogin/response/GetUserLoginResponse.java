package com.sprite.cloud.automation.framework.base.api.models.petstore.v2.user.getuserlogin.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserLoginResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GetUserLoginResponse{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}