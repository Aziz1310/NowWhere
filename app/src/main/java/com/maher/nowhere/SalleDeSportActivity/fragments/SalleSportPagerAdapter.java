package com.maher.nowhere.SalleDeSportActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class SalleSportPagerAdapter extends FragmentStatePagerAdapter {

    int numTab;

    public SalleSportPagerAdapter (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numTab = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AProposSalleFragment aProposSalle = new AProposSalleFragment();
                return aProposSalle;
            case 1:
                PhotosFragment photos = new PhotosFragment();
                return photos;
            case 2:
                TrynowFragment trynow = new TrynowFragment();
                return trynow;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
