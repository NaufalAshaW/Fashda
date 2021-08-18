package com.nasha.fashda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nasha.fashda.Adapter.PhotoAdapter;
import com.nasha.fashda.databinding.ActivityMosqueDetailBinding;
import com.nasha.fashda.models.PhotoModel;
import com.nasha.fashda.models.PlaceModel;
import com.nasha.fashda.presenters.MosqueDetailPresenter;
import com.nasha.fashda.views.MosqueDetailView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MosqueDetailActivity extends AppCompatActivity implements MosqueDetailView {
    private ActivityMosqueDetailBinding binding;
    private MosqueDetailPresenter mosqueDetailPresenter;
    private PhotoAdapter adapter;

    private ArrayList<String> photos = new ArrayList<>();
    private String placeId;
    private String placeName;
    private String placePhotoUrl;
    private PlaceModel mosque;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMosqueDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        placeId = bundle.getString("place_id");
        placeName = bundle.getString("place_name");
        placePhotoUrl = bundle.getString("place_photo_url");

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(placeName);

            if (placePhotoUrl != null) {
                Picasso.get()
                        .load(placePhotoUrl)
                        .placeholder(R.color.main_green)
                        .into(binding.placePhoto);
            } else {
                binding.placePhoto.setImageResource(R.drawable.placeholder_mosque);
            }
        }

        mosqueDetailPresenter = new MosqueDetailPresenter(this);

        adapter = new PhotoAdapter(photos);
        binding.listPhotos.setAdapter(adapter);

        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mosque != null) {
                    if (mosque.getFormattedPhoneNumber() != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(
                                String.format("tel:%s", mosque.getFormattedPhoneNumber())
                        ));

                        startActivity(intent);
                    } else {
                        new AlertDialog.Builder(MosqueDetailActivity.this)
                                .setTitle("Notification")
                                .setMessage("This place has no phone number.")
                                .setPositiveButton("Ok", null)
                                .create().show();
                    }
                } else {
                    Toast.makeText(MosqueDetailActivity.this, "Loading . . .", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        binding.btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mosque != null) {
                    if (mosque.getGeometry() != null) {
                        Uri gmap = Uri.parse(
                                String.format("google.navigation:q=%s,%s",
                                        mosque.getGeometry().getLocation().getLat(),
                                        mosque.getGeometry().getLocation().getLng())
                        );
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmap);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if (mapIntent.resolveActivity(getPackageManager()) != null) {

                            startActivity(mapIntent);
                        } else {
                            new AlertDialog.Builder(MosqueDetailActivity.this)
                                    .setTitle("Notification")
                                    .setMessage("Google Map is not installed")
                                    .setPositiveButton("Ok", null)
                                    .create().show();
                        }
                    }
                } else {
                    Toast.makeText(MosqueDetailActivity.this, "Loading . . .", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        mosqueDetailPresenter.load();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void parsePhotos() {
        if (mosque.getPhotos() == null) return;

        photos.clear();

        for (PhotoModel photo : mosque.getPhotos()) {
            photo.setMaxWidth(400);
            photos.add(photo.getPhotoUrl());
        }

        if (photos.size() > 0) binding.noPhoto.setVisibility(View.GONE);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoad(PlaceModel mosqueData) {
        mosque = mosqueData;
        parsePhotos();
        binding.address.setText(mosque.getFormattedAddress());
    }

    @Override
    public ActivityMosqueDetailBinding getBinding() {
        return binding;
    }

    @Override
    public String getPlaceId() {
        return placeId;
    }

    @Override
    public String getPlaceName() {
        return placeName;
    }
}
