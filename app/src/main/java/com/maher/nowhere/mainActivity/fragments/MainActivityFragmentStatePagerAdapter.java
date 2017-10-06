package com.maher.nowhere.mainActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.mainActivity.fragments.Accueil;
import com.maher.nowhere.mainActivity.fragments.Categories;
import com.maher.nowhere.mainActivity.fragments.Weeklik;

/**
 * Created by RaniaH on 06/10/2017.
 */

public class MainActivityFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    public MainActivityFragmentStatePagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Weeklik weeklik = new Weeklik();
                return weeklik;
            case 1:
                Accueil accueil = new Accueil();
                return accueil;
            case 2:
                Categories categories = new Categories();
                return categories;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
