package com.nasha.fashda.presenters;

import android.app.AlertDialog;
import android.content.Context;

import com.nasha.fashda.API.ApiEndpoint;
import com.nasha.fashda.API.ApiService;
import com.nasha.fashda.models.NearbySearchResponseModel;
import com.nasha.fashda.models.PlaceModel;
import com.nasha.fashda.views.MosqueView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MosqueSearchResultPresenter {

    private List<PlaceModel> mosquePlaces = new ArrayList<>();
    private MosqueView mosqueView;
    private ApiService api;
    private Context context;

    public static NearbySearchResponseModel mosqueBody;

    public MosqueSearchResultPresenter(MosqueView mosqueView){
        this.mosqueView = mosqueView;

        api = ApiEndpoint.getPlaceClient().create(ApiService.class);
//        mosqueView.getBinding().rvMosque.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (mosqueBody == null) return;
//
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                if (!isLoading && mosqueBody.getNextPageToken()!= null){
//                    if (layoutManager != null && layoutManager.findLastVisibleItemPosition() == mosquePlaces.size()-1){
//                        fetchPlaceData(mosqueBody.getNextPageToken());
//                    }
//
//                }
//            }
//        });

    }


    public void fetchPlaceData(double latitude, double longitude){
        Map<String, String> options = new HashMap<>();
        options.put("location", String.format("%s,%s",String.valueOf(latitude),String.valueOf(longitude)));
        options.put("type", "mosque");
        options.put("radius", "1000");

        Call<NearbySearchResponseModel> call = api.getNearbySearch(options);
        call.enqueue(new Callback<NearbySearchResponseModel>() {
            @Override
            public void onResponse(Call<NearbySearchResponseModel> call, Response<NearbySearchResponseModel> response) {
                mosqueBody = response.body();
                if (mosqueBody.getStatus().equals("OK")){
                    mosquePlaces = mosqueBody.getResults();
                    mosqueView.onLoad(mosquePlaces);
                }
            }

            @Override
            public void onFailure(Call<NearbySearchResponseModel> call, Throwable t) {
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
