package com.nasha.fashda.presenters;

import android.app.AlertDialog;

import com.nasha.fashda.API.ApiEndpoint;
import com.nasha.fashda.API.ApiService;
import com.nasha.fashda.models.PlaceModel;
import com.nasha.fashda.models.PlaceResponseModel;
import com.nasha.fashda.views.MosqueDetailView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MosqueDetailPresenter implements MosquePresenter {
    MosqueDetailView detailView;
    PlaceModel mosque;
    ApiService api;

    public MosqueDetailPresenter(MosqueDetailView detailView){
        this.detailView = detailView;
        api = ApiEndpoint.getPlaceClient().create(ApiService.class);
    }

    @Override
    public void load() {
        Map<String, String> options = new HashMap<>();
        options.put("language", "en");
        options.put("region", ".id");
        options.put("fields","name,place_id,photos,vicinity,geometry,formatted_address,formatted_phone_number,url");

        Call<PlaceResponseModel> call = api.getPlaceDetail(detailView.getPlaceId(), options);
        call.enqueue(new Callback<PlaceResponseModel>() {
            @Override
            public void onResponse(Call<PlaceResponseModel> call, Response<PlaceResponseModel> response) {
                PlaceResponseModel detailBody = response.body();
                if (response.isSuccessful() && detailBody != null){
                    if (detailBody.getStatus().equals("OK")){
                        mosque = detailBody.getResult();
                        detailView.onLoad(mosque);
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceResponseModel> call, Throwable t) {
                new AlertDialog.Builder(null)
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .show();
            }
        });
    }
}
