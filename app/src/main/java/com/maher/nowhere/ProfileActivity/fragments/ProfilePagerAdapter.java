package com.maher.nowhere.ProfileActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RaniaH on 11/10/2017.
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public ProfilePagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PageFragment page = new PageFragment();
                return page;
            case 1:
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                ReservationsFragment reservations = new ReservationsFragment();
                return  reservations;
            case 3:
                FavorisFragment favoris = new FavorisFragment();
                return favoris;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
