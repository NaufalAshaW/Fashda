package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class PlaceModel {

    @SerializedName("name")
    private String placeName;

    private double distance;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("photos")
    private PhotoModel[] photos;

    @SerializedName("vicinity")
    private String vicinity;

    @SerializedName("geometry")
    private GeometryModel geometry;

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("formatted_phone_number")
    private String formattedPhoneNumber;

    @SerializedName("url")
    private String url;

    @SerializedName("business_status")
    private String businessStatus;

    @SerializedName("opening_hours")
    private OpeningHourModel openingHours;

    private String thumbnail;
    private String address;
    private String date;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public PhotoModel[] getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoModel[] photos) {
        this.photos = photos;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public GeometryModel getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryModel geometry) {
        this.geometry = geometry;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public OpeningHourModel getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHourModel openingHours) {
        this.openingHours = openingHours;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
