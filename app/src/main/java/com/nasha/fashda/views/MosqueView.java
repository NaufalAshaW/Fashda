package com.nasha.fashda.views;


import com.nasha.fashda.databinding.FragmentMosqueBinding;
import com.nasha.fashda.models.PlaceModel;

import java.util.List;

public interface MosqueView {
    FragmentMosqueBinding getBinding();
    String getType();
    void onLoad(List<PlaceModel> mosquePlaces);
}
