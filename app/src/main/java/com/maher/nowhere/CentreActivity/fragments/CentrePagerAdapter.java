package com.maher.nowhere.CentreActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.CentreActivity.fragments.products.ProductsFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.RestaurantProfileActivity.fragments.AProposFragment;
import com.maher.nowhere.model.Owner;

/**
 * Created by RaniaH on 25/11/2017.
 */

public class CentrePagerAdapter extends FragmentStatePagerAdapter {

    int numTab;
    Owner owner;
    String categorie;
    public  CentrePagerAdapter(FragmentManager fm, int NumberOfTabs, Owner owner,String categorie){
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
                photosFragment.setArguments(bundle);

                return photosFragment;
            case 2:
                ProductsFragment products = new ProductsFragment();
                products.setArguments(bundle);

                products.setArguments(bundle);
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
