package com.nasha.fashda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.nasha.fashda.API.ApiConfig;
import com.nasha.fashda.presenters.SearchResultPresenter;

import java.util.Arrays;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private SearchResultPresenter searchResultPresenter;
    private Button findBtn;
    private PrayerFragment prayerFragment;
    private MosqueFragment mosqueFragment;
    MeowBottomNavigation meowNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.main_layout);

        prayerFragment = new PrayerFragment();
        mosqueFragment = new MosqueFragment();
        searchResultPresenter = new SearchResultPresenter(prayerFragment);

        meowNav = findViewById(R.id.nav);
        meowNav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_access_time_24));
        meowNav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_place_24));


        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), ApiConfig.API_KEY, Locale.US);
        }
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener(){

            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() +
                        ", " + place.getLatLng().latitude +
                        ", " +place.getLatLng().longitude);
                searchResultPresenter.fetchPrayData(place.getLatLng().latitude,place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        meowNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragment = prayerFragment;
                        break;
                    case 2 :
                        fragment = mosqueFragment;
                        break;
                }
                loadFragment(fragment);
            }
        });
        meowNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragment = prayerFragment;
                        break;
                    case 2 :
                        fragment = mosqueFragment;
                        break;
                }
                loadFragment(fragment);
            }
        });
        meowNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragment = prayerFragment;
                        break;
                    case 2 :
                        fragment = mosqueFragment;
                        break;
                }
                loadFragment(fragment);
            }
        });
        meowNav.show(1,true);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();
    }
}

