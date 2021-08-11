package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class PrayModel {

    @SerializedName("timings")
    private TimingsModel timings;

    public TimingsModel getTimings() {
        return timings;
    }

    public void setTimings(TimingsModel timings) {
        this.timings = timings;
    }
}
