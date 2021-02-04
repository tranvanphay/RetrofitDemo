package com.phaytran.demoretrofit.model;

import com.google.gson.annotations.SerializedName;

public class AddTodoRes {
    @SerializedName("is_success)")
    private String isSuccess;

    public AddTodoRes(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}
