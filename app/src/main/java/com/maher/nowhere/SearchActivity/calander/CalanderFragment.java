package com.maher.nowhere.SearchActivity.calander;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.maher.nowhere.R;

import com.maher.nowhere.ContactsActivity.ContactActivity;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.ProfileFriendActivity.ProfileFriendActivity;
import com.maher.nowhere.login.LoginActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.sideMenu.NavigationDrawerAdapter;
import com.maher.nowhere.utiles.RecyclerItemClickListener;

public class CalanderFragment extends Fragment {


    private ActionBarDrawerToggle mDrawerToggle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calander, container, false);
        setUpRecyclerView(view);
        return view;
    }



    private RecyclerView setUpRecyclerView(View view) {


        CalanderAdapter adapter = new CalanderAdapter(getActivity(), CalanderItem.getData());




        RecyclerView recyclerView = view.findViewById(R.id.rvSettings);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return recyclerView;
    }


    public void setUpDrawer(DrawerLayout drawerLayout, Toolbar toolbar) {
        DrawerLayout mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }




}
