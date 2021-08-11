package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class PrayResponseModel {
//    public PrayModel data;

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private PrayModel data;

//    public PrayModel getResults() {
//        return results;
//    }
//
//    public void setResults(PrayModel results) {
//        this.results = results;
//    }

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
