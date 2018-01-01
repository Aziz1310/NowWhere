package com.maher.nowhere.CentreActivity.fragments.products;

import android.content.Context;

import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilInteractor;
import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilView;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 14/11/2017.
 */

public class ProductsPresenter implements ProduitInteractor.OnFinishedListener {

    private ProduitView produitView;
    private ProduitInteractor produitInteractor;
    private Context mcContext;

    public ProductsPresenter(ProduitView accueilView, Context mcContext) {
        this.produitView = accueilView;
        this.mcContext = mcContext;
        produitInteractor = new ProduitInteractor();
    }


    public void getListProduit(int idUser) {
        produitView.showProgress();
            produitInteractor.getListProduit(idUser, this,mcContext);
        }



    @Override
    public void onSuccess(ArrayList<Product> products) {
        produitView.hideProgress();
        if(!products.isEmpty())
            produitView.loadAllProducts(products);
        else produitView.loadNoProduct(products);

    }

    @Override
    public void onError() {
        produitView.hideProgress();
        produitView.networkError();
    }


}
