package com.maher.nowhere.CentreActivity.fragments.products;

import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface ProduitView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllProducts(ArrayList<Product> produitViews);
    void loadNoProduct(ArrayList<Product> products);

}
