package com.example.vigoinfotechassignment.Adapter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vigoinfotechassignment.Fragments.TabFragment1;
import com.example.vigoinfotechassignment.Fragments.TabFragment2;
import com.example.vigoinfotechassignment.Fragments.TabFragment3;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private Bundle dataBundle;

    public TabPagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.dataBundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        // Create a fragment instance for each tab with the corresponding data
        switch (position) {
            case 0:
                TabFragment1 fragment1 = new TabFragment1();
                fragment1.setArguments(dataBundle);
                return fragment1;

            case 1:
                TabFragment2 fragment2 = new TabFragment2();
                fragment2.setArguments(dataBundle);
                return fragment2;

            case 2:
                TabFragment3 fragment3 = new TabFragment3();
                fragment3.setArguments(dataBundle);
                return fragment3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Set tab titles dynamically based on the provided TabLayout in activity_main.xml
        switch (position) {
            case 0:
                return "Tab 1";
            case 1:
                return "Tab 2";
            case 2:
                return "Tab 3";
            default:
                return null;
        }
    }
}
