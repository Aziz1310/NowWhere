package com.maher.nowhere.MapActivity;

import android.content.Context;

import com.maher.nowhere.mainActivity.fragments.weeklik.WeeklikInteractor;
import com.maher.nowhere.mainActivity.fragments.weeklik.WeeklikView;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class MapPresenter implements MapInteractor.OnMapFinishedListener {

    private MapView mapView;
    private MapInteractor mapInteractor;
    private Context mcContext;

    public MapPresenter(MapView mapView, Context mcContext) {
        this.mapView = mapView;
        this.mcContext = mcContext;
        mapInteractor = new MapInteractor();
    }


    void loadAllPosts(Double lat,Double lang,String type,int distance) {
        mapView.showProgress();
        mapInteractor.loadAllPosts(lat,lang,type,distance,this,mcContext);
    }


    @Override
    public void onSuccess(ArrayList<Owner> posts,int mapDrawable) {
        mapView.hideProgress();
        if (!posts.isEmpty())
            mapView.loadAllPosts(posts,mapDrawable);
        else mapView.loadNoPosts();
    }

    @Override
    public void onError() {
        mapView.hideProgress();
        mapView.loadNoPosts();
    }
}
