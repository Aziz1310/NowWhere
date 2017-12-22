package com.maher.nowhere.CentreActivity.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class CentrePagerAdapter extends FragmentStatePagerAdapter {

    int numTab;
    public  CentrePagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.numTab = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AProposCentreFragment aProposCentre = new AProposCentreFragment();
                return aProposCentre;
            case 1:
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                ProductsFragment products = new ProductsFragment();
                return products;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
