package com.example.vigoinfotechassignment.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.vigoinfotechassignment.Adapter.TabPagerAdapter;
import com.example.vigoinfotechassignment.R;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Set up ViewPager with Adapter
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), getIntent().getExtras());
        viewPager.setAdapter(adapter);

        // Link ViewPager and TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }
}