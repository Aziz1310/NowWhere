package com.maher.nowhere.SalleDeSportActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.SalleDeSportActivity.fragments.tryNow.TrynowFragment;
import com.maher.nowhere.model.Owner;

/**
 * Created by RaniaH on 24/11/2017.
 */

public class SalleSportPagerAdapter extends FragmentStatePagerAdapter {

    int numTab;
    Owner owner;
    String categorie;

    public SalleSportPagerAdapter (FragmentManager fm, int NumberOfTabs,Owner owner, String categorie){
        super(fm);
        this.numTab = NumberOfTabs;
        this.owner=owner;
        this.categorie=categorie;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("owner",owner);
        switch (position){
            case 0:
                AProposSalleFragment aProposSalle = new AProposSalleFragment();
                bundle.putString("categorie",categorie);
                aProposSalle.setArguments(bundle);
                return aProposSalle;
            case 1:
                PhotosFragment photosFragment = new PhotosFragment();
                bundle.putString("from",PhotosFragment.FROM_PRESTATAIRE);
                photosFragment.setArguments(bundle);
                return photosFragment;
            case 2:
                TrynowFragment trynow = new TrynowFragment();
                trynow.setArguments(bundle);
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
