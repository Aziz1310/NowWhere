package com.maher.nowhere.eventPlaceActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.CinemaActivity.fragments.ProchainementFragment;

/**
 * Created by RaniaH on 22/11/2017.
 */

public class EventPlacePagerAdapter extends FragmentStatePagerAdapter {

    int numTab;
    String categorie;

    public EventPlacePagerAdapter(FragmentManager fm, int NumberOfTabs, String categorie) {
        super(fm);
        this.numTab = NumberOfTabs;
        this.categorie = categorie;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("categorie", categorie);
        switch (position) {
            case 0:
                PlacesFragment placesFragment = new PlacesFragment();
                placesFragment.setArguments(bundle);
                return placesFragment;

            case 1:
                EventFragment eventFragment = new EventFragment();
                eventFragment.setArguments(bundle);
                return eventFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
