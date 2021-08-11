package com.nasha.fashda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nasha.fashda.models.PrayModel;
import com.nasha.fashda.models.TimingsModel;
import com.nasha.fashda.views.PrayerView;

public class PrayerFragment extends Fragment implements PrayerView {
    private TextView fajr, duhr, ashr, maghrib, isha;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prayer,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        fajr = view.findViewById(R.id.fajr);
        duhr = view.findViewById(R.id.dhuhr);
        ashr = view.findViewById(R.id.ashr);
        maghrib = view.findViewById(R.id.maghrib);
        isha = view.findViewById(R.id.isha);
        view.setVisibility(View.GONE);
    }

    @Override
    public void onPrayDataLoad(PrayModel prayData) {
        if(view != null) {
            final TimingsModel timings = prayData.getTimings();
            fajr.setText(
                    String.format("Subuh: %s", timings.getFajr())
            );
            duhr.setText(
                    String.format("Dzuhur: %s", timings.getDhuhr())
            );
            ashr.setText(
                    String.format("Ashar: %s", timings.getAsr())
            );
            maghrib.setText(
                    String.format("Maghrib: %s", timings.getMaghrib())
            );
            isha.setText(
                    String.format("Isya: %s", timings.getIsha())
            );
            view.setVisibility(View.VISIBLE);
        }
    }
}
