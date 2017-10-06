package com.maher.nowhere;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by RaniaH on 06/10/2017.
 */

public class ViewActivity extends FragmentStatePagerAdapter {

    int numOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
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
