package com.example.patientmodule.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientsMainModel {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private List<PatientsDetailsModel> list = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<PatientsDetailsModel> getPatientsList() {
        return list;
    }

    public void setPatientsList(List<PatientsDetailsModel> message) {
        this.list = message;
    }

}