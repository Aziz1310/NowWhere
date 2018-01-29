package com.maher.nowhere.SalleDeSportActivity.fragments.tryNow;

import android.content.Context;

import com.maher.nowhere.CentreActivity.fragments.products.ProduitInteractor;
import com.maher.nowhere.CentreActivity.fragments.products.ProduitView;
import com.maher.nowhere.model.Pack;
import com.maher.nowhere.model.Product;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class TryNowPresenter implements TryNowInteractor.OnFinishedListener {

    private TryNowView tryNowView;
    private TryNowInteractor tryNowInteractor;
    private Context mcContext;

    public TryNowPresenter(TryNowView tryNowView, Context mcContext) {
        this.tryNowView = tryNowView;
        this.mcContext = mcContext;
        tryNowInteractor = new TryNowInteractor();
    }


    public void getListPacks(int idUser) {
        tryNowView.showProgress();
            tryNowInteractor.getListPacks(idUser, this,mcContext);
        }



    @Override
    public void onSuccess(ArrayList<Pack> packs) {
        tryNowView.hideProgress();
        if(!packs.isEmpty())
            tryNowView.loadAllPacks(packs);
        else tryNowView.loadNoPack(packs);

    }

    @Override
    public void onError() {
        tryNowView.hideProgress();
        tryNowView.networkError();
    }


}
