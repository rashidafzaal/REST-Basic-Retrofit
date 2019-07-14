package com.example.patientmodule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseReturnModel {

    //===========================

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getError() {
        return error;
    }

    public void setMessage(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setErrorMsg(String errorMsg) {
        this.message = errorMsg;
    }

}
