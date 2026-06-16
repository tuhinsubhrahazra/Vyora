package com.tuhin.vyora.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tuhin.vyora.Fragments.HistoryFragment;
import com.tuhin.vyora.Fragments.HomeFragment;
import com.tuhin.vyora.R;
import com.tuhin.vyora.Fragments.SettingsFragment;
import com.tuhin.vyora.Fragments.TrackersFragment;

public class DashboardActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottomNav);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboard_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0); 
            return insets;
        });

        // Add extra bottom padding to bottomNav so it doesn't overlap with system nav bar
        ViewCompat.setOnApplyWindowInsetsListener(bottomNav, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, 0, 0, systemBars.bottom);
            return insets;
        });

        viewPager.setAdapter(new ViewPagerAdapter(this));
        
        // Optional: Disable swiping if you only want the animation on tab click
        // viewPager.setUserInputEnabled(false); 

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            int targetPos = -1;

            if (id == R.id.nav_home) targetPos = 0;
            else if (id == R.id.nav_trackers) targetPos = 1;
            else if (id == R.id.nav_history) targetPos = 2;
            else if (id == R.id.nav_settings) targetPos = 3;

            if (targetPos != -1 && viewPager.getCurrentItem() != targetPos) {
                viewPager.setCurrentItem(targetPos, true);
            }
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int id = R.id.nav_home;
                switch (position) {
                    case 0: id = R.id.nav_home; break;
                    case 1: id = R.id.nav_trackers; break;
                    case 2: id = R.id.nav_history; break;
                    case 3: id = R.id.nav_settings; break;
                }
                if (bottomNav.getSelectedItemId() != id) {
                    bottomNav.setSelectedItemId(id);
                }
            }
        });
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return new HomeFragment();
                case 1: return new TrackersFragment();
                case 2: return new HistoryFragment();
                case 3: return new SettingsFragment();
                default: return new HomeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
