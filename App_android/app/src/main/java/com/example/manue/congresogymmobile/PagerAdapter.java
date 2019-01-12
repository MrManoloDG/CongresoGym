package com.example.manue.congresogymmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentDia1 tab1 = new FragmentDia1();
                return tab1;
            case 1:
                FragmentDia2 tab2 = new FragmentDia2();
                return tab2;
            case 2:
                FragmentDia3 tab3 = new FragmentDia3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
