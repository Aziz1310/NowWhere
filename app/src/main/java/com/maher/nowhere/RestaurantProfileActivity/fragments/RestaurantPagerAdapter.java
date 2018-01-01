package com.maher.nowhere.RestaurantProfileActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.RestaurantProfileActivity.fragments.feedback.FeedbackFragment;
import com.maher.nowhere.RestaurantProfileActivity.fragments.menu.MenuFragment;
import com.maher.nowhere.model.Owner;

/**
 * Created by RaniaH on 05/11/2017.
 */

public class RestaurantPagerAdapter extends FragmentStatePagerAdapter {
    int numTab;
    Owner owner;
    String categorie;

    public RestaurantPagerAdapter(FragmentManager fm, int NumberOfTabs, Owner owner, String categorie){
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
                AProposFragment aProposFragment = new AProposFragment();
                bundle.putString("categorie",categorie);
                aProposFragment.setArguments(bundle);
                return aProposFragment;
            case 1:
                PhotosFragment photosFragment = new PhotosFragment();
                bundle.putString("from",PhotosFragment.FROM_PRESTATAIRE);
                photosFragment.setArguments(bundle);
                return photosFragment;
            case 2:
                MenuFragment menu = new MenuFragment();
                menu.setArguments(bundle);
                return menu;
            case 3:
                FeedbackFragment feedback = new FeedbackFragment();
                feedback.setArguments(bundle);
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
