package com.maher.nowhere.ContactsActivity.fragments;

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
                AmisFragment amis = new AmisFragment();
                return amis;
            case 1:
                InvitationsFragment invitations = new InvitationsFragment();
                return invitations;
            case 2:
                SuggestionsFragment suggestions = new SuggestionsFragment();
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
