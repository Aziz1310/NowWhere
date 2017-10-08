package com.maher.nowhere.mainActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by RaniaH on 08/10/2017.
 */

public class ContactPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public ContactPagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Amis amis = new Amis();
                return amis;
            case 1:
                Invitations invitations = new Invitations();
                return invitations;
            case 2:
                Suggestions suggestions = new Suggestions();
                return suggestions;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
