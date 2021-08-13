package com.nasha.fashda.API;

import com.nasha.fashda.models.NearbySearchResponseModel;
import com.nasha.fashda.models.PlaceResponseModel;
import com.nasha.fashda.models.PrayResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET(ApiConfig.PRAY_TIMINGS)
    Call<PrayResponseModel> getPrayerTimings(@Path("timestamp") long timestamp, @QueryMap Map<String, String> options);

    @GET(ApiConfig.PATH_NEARBY_SEARCH)
    Call<NearbySearchResponseModel> getNearbySearch(@QueryMap Map<String, String> options);

    @GET(ApiConfig.PATH_PLACE_DETAIL)
    Call<PlaceResponseModel> getPlaceDetail(@Query("place_id") String placeId, @QueryMap Map<String, String> options);
}
