package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class TimingsModel {

    @SerializedName("Fajr")
    private String fajr;

    @SerializedName("Dhuhr")
    private String dhuhr;

    @SerializedName("Asr")
    private String asr;

    @SerializedName("Sunset")
    private String sunset;

    @SerializedName("Maghrib")
    private String maghrib;

    @SerializedName("Isha")
    private String isha;

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

}
