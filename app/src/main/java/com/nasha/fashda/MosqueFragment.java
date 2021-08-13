package com.nasha.fashda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.nasha.fashda.Adapter.MosqueAdapter;
import com.nasha.fashda.databinding.FragmentMosqueBinding;
import com.nasha.fashda.models.PlaceModel;
import com.nasha.fashda.views.MosqueView;

import java.util.ArrayList;
import java.util.List;

public class MosqueFragment extends Fragment implements MosqueView {

    private List<PlaceModel> mosqueResults= new ArrayList<>();
    private FragmentMosqueBinding fragmentMosqueBinding;
    private MosqueAdapter mosqueAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentMosqueBinding = FragmentMosqueBinding.inflate(getLayoutInflater());
        View view = fragmentMosqueBinding.getRoot();

        mosqueAdapter = new MosqueAdapter(mosqueResults);
        fragmentMosqueBinding.rvMosque.setAdapter(mosqueAdapter);

        return view;

    }

    @Override
    public FragmentMosqueBinding getBinding() {
        return fragmentMosqueBinding;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void onLoad(List<PlaceModel> mosquePlaces) {
        mosqueResults.clear();
        mosqueResults.addAll(mosquePlaces);
        mosqueAdapter.notifyDataSetChanged();
    }
}
