package com.nasha.fashda.models;

import com.google.gson.annotations.SerializedName;

public class PlaceModel {

    @SerializedName("name")
    private String placeName;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("photos")
    private PhotoModel[] photos;

    @SerializedName("geometry")
    private GeometryModel geometry;

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("formatted_phone_number")
    private String formattedPhoneNumber;

    @SerializedName("url")
    private String url;


    private String thumbnail;
    private String address;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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

}
