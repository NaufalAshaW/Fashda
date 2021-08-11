package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class OpeningHourModel {

    @SerializedName("open_now")
    private boolean openNow;

    @SerializedName("weekday_text")
    private String[] weekdayText;

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public String[] getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(String[] weekdayText) {
        this.weekdayText = weekdayText;
    }


}
