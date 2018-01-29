package com.maher.nowhere.SalleDeSportActivity.fragments.tryNow;

import com.maher.nowhere.model.Pack;
import com.maher.nowhere.model.Product;

import java.util.ArrayList;

/**
 * Created by maher on 05/12/2017.
 */

public interface TryNowView {

    void showProgress();
    void hideProgress();
    void networkError();
    void loadAllPacks(ArrayList<Pack> packs);
    void loadNoPack(ArrayList<Pack> packs);

}
