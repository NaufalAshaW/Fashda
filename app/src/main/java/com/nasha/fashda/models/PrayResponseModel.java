package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class PrayResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private PrayModel data;

    public PrayResponseModel(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PrayModel getData() {
        return data;
    }

    public void setData(PrayModel data) {
        this.data = data;
    }
}
