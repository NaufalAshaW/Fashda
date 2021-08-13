package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearbySearchResponseModel {

    public List<PlaceModel> results;

    @SerializedName("status")
    private String status;

    @SerializedName("info_messages")
    private String[] infoMessages;

    public NearbySearchResponseModel(String status, String[] infoMessages) {
        this.status = status;
        this.infoMessages = infoMessages;
    }

    public List<PlaceModel> getResults() {
        return results;
    }

    public void setResults(List<PlaceModel> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getInfoMessages() {
        return infoMessages;
    }

    public void setInfoMessages(String[] infoMessages) {
        this.infoMessages = infoMessages;
    }

}
