package com.maher.nowhere.ProfileFriendActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.model.User;

/**
 * Created by RaniaH on 30/10/2017.
 */

public class ProfileFriendPagerAdapter extends FragmentStatePagerAdapter {
    int num;
    User user;

    public ProfileFriendPagerAdapter(FragmentManager fm, int NumberOfTabs,User user){
        super(fm);
        this.num = NumberOfTabs;
        this.user=user;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("friend_id",user);
        switch (position){
            case 0:
                MurFragment mur = new MurFragment();
                mur.setArguments(bundle);
                return mur;
            case 1:
                PhotosFragment photos = new PhotosFragment();
                bundle.putString("from",PhotosFragment.FROM_FRIEND_PROFILE);
                photos.setArguments(bundle);

                return  photos;
            case 2:
                AmisFriendFragment amis = new AmisFriendFragment();
                amis.setArguments(bundle);

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
