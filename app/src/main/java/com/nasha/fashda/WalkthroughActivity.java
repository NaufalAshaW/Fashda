package com.nasha.fashda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;
import com.nasha.fashda.Adapter.WalkthroughVPAdapter;
import com.nasha.fashda.databinding.ActivityWalkthroughBinding;
import com.nasha.fashda.models.WalkthroughModel;
import com.nasha.fashda.utils.Preferences;

import java.util.ArrayList;
import java.util.List;

public class WalkthroughActivity extends AppCompatActivity {

    private final List<WalkthroughModel> walkthroughs = new ArrayList<>();
    private ActivityWalkthroughBinding binding;
    private TabLayoutMediator tabMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // view binding
        binding = ActivityWalkthroughBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        WalkthroughVPAdapter adapter = new WalkthroughVPAdapter(walkthroughs);
        binding.wtViewPager.setAdapter(adapter);

        tabMediator = new TabLayoutMediator(binding.wtTab, binding.wtViewPager, (tab, position) -> {});

        binding.wtBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWalk();
            }
        });

        binding.wtViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.wtBtnPrev.setClickable(position > 0);
                binding.wtBtnSkip.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            }
        });

        binding.wtBtnNext.setOnClickListener(v -> {
            if(binding.wtViewPager.getCurrentItem()+1 < walkthroughs.size()) {
                binding.wtViewPager.setCurrentItem(binding.wtViewPager.getCurrentItem() + 1);
            } else {
                finishWalk();
            }
        });

        binding.wtBtnPrev.setOnClickListener(v -> {
            if(binding.wtViewPager.getCurrentItem() > 0) {
                binding.wtViewPager.setCurrentItem(binding.wtViewPager.getCurrentItem() - 1);
            }
        });

        load();
    }

    public void finishWalk() {
        Preferences.setWalkedStatus(this, true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void load() {
        this.walkthroughs.clear();

        WalkthroughModel model1 = new WalkthroughModel();
        model1.setTitle("Prayer Times");
        model1.setDescription("Find prayer times based on the location.");
        model1.setImageDrawable(R.drawable.ic_undraw_adventure_4hum);

        WalkthroughModel model2 = new WalkthroughModel();
        model2.setTitle("Mosque Near You");
        model2.setDescription("Find the nearest mosque from your location.");
        model2.setImageDrawable(R.drawable.ic_undraw_lost_online_re_upmy);

        WalkthroughModel model3 = new WalkthroughModel();
        model3.setTitle("Place Details");
        model3.setDescription("You can check the details of a mosque.");
        model3.setImageDrawable(R.drawable.ic_undraw_goals_re_lu76);

        walkthroughs.add(model1);
        walkthroughs.add(model2);
        walkthroughs.add(model3);
        tabMediator.attach();
    }

}
