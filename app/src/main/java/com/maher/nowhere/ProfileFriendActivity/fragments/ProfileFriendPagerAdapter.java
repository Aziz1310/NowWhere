package com.maher.nowhere.ProfileFriendActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RaniaH on 30/10/2017.
 */

public class ProfileFriendPagerAdapter extends FragmentStatePagerAdapter {
    int num;

    public ProfileFriendPagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.num = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MurFragment mur = new MurFragment();
                return mur;
            case 1:
                PhotosFragment photos = new PhotosFragment();
                return  photos;
            case 2:
                AmisFriendFragment amis = new AmisFriendFragment();
                return amis;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
