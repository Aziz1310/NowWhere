package com.maher.nowhere.RestaurantProfileActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RaniaH on 05/11/2017.
 */

public class RestaurantPagerAdapter extends FragmentStatePagerAdapter {
    int numTab;

    public RestaurantPagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numTab = NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AProposFragment aProposFragment = new AProposFragment();
                return aProposFragment;
            case 1:
                com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment photosFragment = new com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment();
                return photosFragment;
            case 2:
                MenuFragment menu = new MenuFragment();
                return menu;
            case 3:
                FeedbackFragment feedback = new FeedbackFragment();
                return feedback;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
