package com.nasha.fashda.presenters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.nasha.fashda.API.ApiEndpoint;
import com.nasha.fashda.API.ApiService;
import com.nasha.fashda.models.PlaceModel;
import com.nasha.fashda.models.PrayModel;
import com.nasha.fashda.models.PrayResponseModel;
import com.nasha.fashda.views.MainView;
import com.nasha.fashda.views.MosqueView;
import com.nasha.fashda.views.PrayerView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultPresenter {
    private List<PlaceModel> places = new ArrayList<>();
    private PrayModel prayModel;
    private MosqueView mosqueView;
    private PrayerView prayerView;
    private Context context;
    private ApiService api;


    public static PrayResponseModel body;

    public SearchResultPresenter(PrayerView prayerView){
        this.prayerView = prayerView;

        api = ApiEndpoint.getPrayClient().create(ApiService.class);

    }

    public SearchResultPresenter(MosqueView mosqueView){
        this.mosqueView = mosqueView;

        api = ApiEndpoint.getPlaceClient().create(ApiService.class);



    }

    public void fetchPlaceData(){

    }

    public void fetchPrayData(double latitude, double longitude){
        Map<String, String> options = new HashMap<>();
        options.put("latitude",String.valueOf(latitude));
        options.put("longitude",String.valueOf(longitude));
        options.put("method","11");

        Log.d("awawfaw", String.valueOf(new Date().getTime()));


        Call<PrayResponseModel> call = api.getPrayerTimings(new Date().getTime()/1000, options);
        call.enqueue(new Callback<PrayResponseModel>() {
            @Override
            public void onResponse(Call<PrayResponseModel> call, Response<PrayResponseModel> response) {
                body = response.body();
                if (body.getStatus().equals("OK")){
                    prayModel = body.getData();
                    prayerView.onPrayDataLoad(prayModel);
                }
            }

            @Override
            public void onFailure(Call<PrayResponseModel> call, Throwable t) {
                t.printStackTrace();

                if (context!=null){
                    new AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage(t.getMessage())
                            .show();
                }
            }
        });
    }


}
