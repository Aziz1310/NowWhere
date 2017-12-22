package com.maher.nowhere.RestaurantProfileActivity.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.R;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.utiles.ShadowLayout;


public class AProposFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private BitmapDrawable mBitmapDrawable;
    private ColorMatrix colorizerMatrix = new ColorMatrix();
    ColorDrawable mBackground;
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;
    private ImageView mImageView;
    private FrameLayout mTopLevelLayout;
    private ShadowLayout mShadowLayout;
    private int mOriginalOrientation;
    private GoogleMap map;
    private Toolbar toolbar;
    private ImageView btnIgo, img1;
    private Post post;
    private Owner owner;

    private TextView tvTitle;
    private TextView tvPlace, tvDay, tvMonth, tvYear;
    private TextView tvDate, tvAdresse, tvDescription;


    public AProposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AProposFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AProposFragment newInstance(String param1, String param2) {
        AProposFragment fragment = new AProposFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            owner = (Owner) getArguments().getSerializable("owner");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apropos_resto, container, false);

        btnIgo = (ImageView) view.findViewById(R.id.btnIGo);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvAdresse = (TextView) view.findViewById(R.id.tvAdresse);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvPlace = (TextView) view.findViewById(R.id.tvName);


        if (owner != null) {
            tvAdresse.setText(owner.getAdresse());
            tvDate.setText(String.format("Ouvert\n de %s à %s", owner.getHeure_overture(), owner.getHeure_fermeture()));
            tvDescription.setText(owner.getDescription());

            btnIgo.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    Intent intent = new Intent(getActivity(), SearchDetailActivity.class);
                    intent.putExtra("owner", owner);
                    getActivity().startActivity(intent);
                    (getActivity()).overridePendingTransition(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
                }
            });
        }


        MapView mMapView = (MapView) view.findViewById(R.id.map);
        MapsInitializer.initialize(getActivity());

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;
        try {
            boolean success = googleMap.setMapStyle(

                    MapStyleOptions.loadRawResourceStyle(
                            this.getActivity(), R.raw.jsonstyle));

            if (!success) {
                Log.e("vv", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("vv", "Can't find style. Error: ", e);
        }

        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.icon_map_restorant);
        Bitmap b = bitmapdraw1.getBitmap();
        final Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, 40, 60, false);

        MarkerOptions opt1 = new MarkerOptions();

        if (owner != null) {
            opt1.position(new LatLng(owner.getLatitude(), owner.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                    .anchor(0.5f, 1);

            googleMap.addMarker(opt1);

            googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(owner.getLatitude(), owner.getLongitude()), 12));
        }


        googleMap.getUiSettings().setScrollGesturesEnabled(false);

    }


}
