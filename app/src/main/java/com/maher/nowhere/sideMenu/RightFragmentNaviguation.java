package com.maher.nowhere.sideMenu;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ContactsActivity.ContactActivity;
import com.maher.nowhere.Settings.SettingsActivity;
import com.maher.nowhere.chat.ListActivity;
import com.maher.nowhere.login.LoginActivity;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.ProfileFriendActivity.ProfileFriendActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.RestaurantProfileActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.RecyclerItemClickListener;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

public class RightFragmentNaviguation extends Fragment {


    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_naviguation, container, false);
        setUpRecyclerView(view);
        TextView tvName = (TextView) view.findViewById(R.id.title);
        final ImageView img = view.findViewById(R.id.img);
        User user = User.getCurrentUser(getActivity());
        tvName.setText(user.getName());
        Picasso.with(getActivity()).load(Uri.parse(Urls.IMG_URL_USER_COVER + user.getCoverPhoto())).into(img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success cover photo");
            }

            @Override
            public void onError() {
                img.setImageDrawable(getResources().getDrawable(R.drawable.signup_image));
            }
        });

        return view;
    }


    private RecyclerView setUpRecyclerView(View view) {


        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(getActivity(), NavigationDrawerItem.getData());


        RecyclerView recyclerView = view.findViewById(R.id.rvSettings);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), ProfileActivity.class));
                        break;
                   /* case 1:
                        getActivity().startActivity(new Intent(getActivity(), ProfileFriendActivity.class));
                        break;*/
                    case 2:
                        startActivity(new Intent(getActivity(), ListActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), ContactActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), SettingsActivity.class));
                        break;
                    case 5:
                        User.logOut(getActivity());
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                        break;

                    default:
                        getActivity().onBackPressed();
                }

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
