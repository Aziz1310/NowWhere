package com.maher.nowhere.CinemaActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RaniaH on 22/11/2017.
 */

public class CinemaPagerAdapter extends FragmentStatePagerAdapter {

    int numTab;

    public CinemaPagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numTab = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                EnSalleFragment enSalle = new EnSalleFragment();
                return enSalle;
            case 1:
                ProchainementFragment prochainement = new ProchainementFragment();
                return prochainement;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
