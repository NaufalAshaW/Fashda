package com.nasha.fashda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.nasha.fashda.API.ApiConfig;
import com.nasha.fashda.presenters.MosqueSearchResultPresenter;
import com.nasha.fashda.presenters.PrayerSearchResultPresenter;

import java.util.Arrays;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private PrayerSearchResultPresenter prayerSearchResultPresenter;
    private MosqueSearchResultPresenter mosqueSearchResultPresenter;
    private FragmentManager fragmentManager;
    private PrayerFragment prayerFragment;
    private MosqueFragment mosqueFragment;
    MeowBottomNavigation meowNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.main_layout);

        //Keeping the fragment alive
        fragmentManager = getSupportFragmentManager();
        prayerFragment = new PrayerFragment();
        mosqueFragment = new MosqueFragment();

        fragmentManager.beginTransaction()
                .add(R.id.main_frame, prayerFragment)
                .add(R.id.main_frame, mosqueFragment)
                .hide(mosqueFragment)
                .commit();

        //Presenters
        prayerSearchResultPresenter = new PrayerSearchResultPresenter(prayerFragment);
        mosqueSearchResultPresenter = new MosqueSearchResultPresenter(mosqueFragment);

        //Bottom Nav
        meowNav = findViewById(R.id.nav);
        meowNav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_access_time_24));
        meowNav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_place_24));


        //Places Autocomplete
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
                        ", " +place.getLatLng().latitude +
                        ", " +place.getLatLng().longitude);
                prayerSearchResultPresenter.fetchPrayData(place.getLatLng().latitude,place.getLatLng().longitude);
                mosqueSearchResultPresenter.fetchPlaceData(place.getLatLng().latitude,place.getLatLng().longitude);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        //Bottom Nav Listeners
        meowNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragmentManager.beginTransaction()
                                .show(prayerFragment)
                                .hide(mosqueFragment).commit();
                        break;
                    case 2 :
                        fragmentManager.beginTransaction()
                                .show(mosqueFragment)
                                .hide(prayerFragment).commit();
                        break;
                }
            }
        });
        meowNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragmentManager.beginTransaction()
                                .show(prayerFragment)
                                .hide(mosqueFragment).commit();
                        break;
                    case 2 :
                        fragmentManager.beginTransaction()
                                .show(mosqueFragment)
                                .hide(prayerFragment).commit();
                        break;
                }
            }
        });
        meowNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment =  null;

                switch (item.getId()){
                    case 1 :
                        fragmentManager.beginTransaction()
                                .show(prayerFragment)
                                .hide(mosqueFragment).commit();
                        break;
                    case 2 :
                        fragmentManager.beginTransaction()
                                .show(mosqueFragment)
                                .hide(prayerFragment).commit();
                        break;
                }
            }
        });
        meowNav.show(1,true);
    }

//    private void loadFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();
//    }
}

