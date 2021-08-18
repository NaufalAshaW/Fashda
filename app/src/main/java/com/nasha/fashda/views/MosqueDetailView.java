package com.nasha.fashda.views;

import com.nasha.fashda.databinding.ActivityMosqueDetailBinding;
import com.nasha.fashda.models.PlaceModel;

public interface MosqueDetailView {
    void onLoad(PlaceModel mosqueData);

    ActivityMosqueDetailBinding getBinding();

    String getPlaceId();
    String getPlaceName();
}
