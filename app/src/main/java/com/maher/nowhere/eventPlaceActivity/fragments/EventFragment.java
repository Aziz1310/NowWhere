package com.maher.nowhere.eventPlaceActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.SearchPresenter;
import com.maher.nowhere.SearchActivity.SearchView;
import com.maher.nowhere.SearchActivity.adapter.SearchAdapter;
import com.maher.nowhere.SearchActivity.adapter.SearchOwnerAdapter;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Search;
import com.maher.nowhere.model.User;

import java.util.ArrayList;


public class EventFragment extends Fragment implements SearchView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Post> lsearch;
    private String categorie;
    private LottieAnimationView lottieAnimationView;



    public EventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnSalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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
            mParam2 = getArguments().getString(ARG_PARAM2);
            categorie=getArguments().getString("categorie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_en_salle, container, false);
        recyclerView=view.findViewById(R.id.rv_enSalle);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);


        SearchPresenter searchPresenter = new SearchPresenter(this, getActivity());
        if(categorie.equals("Discos"))
        searchPresenter.loadAllEvents(User.getCurrentUser(getActivity()).getId());

        lsearch = new ArrayList<>();
        lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        SearchAdapter searchAdapter = new SearchAdapter(getActivity(), lsearch, categorie);
        recyclerView.setAdapter(searchAdapter);

        return view;
    }
    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void networkError() {

    }

    @Override
    public void loadAllPosts(ArrayList<Owner> owners) {

    }

    @Override
    public void loadAllEvents(ArrayList<Post> posts) {
        System.out.println("load all owners");

        lsearch = new ArrayList<>();
        lsearch = posts;
        SearchAdapter searchOwnerAdapter = new SearchAdapter(getActivity(), lsearch, categorie);
        recyclerView.setAdapter(searchOwnerAdapter);
    }

    @Override
    public void loadNoEvents(ArrayList<Post> posts) {
        lsearch = new ArrayList<>();
        SearchAdapter searchOwnerAdapter = new SearchAdapter(getActivity(), lsearch, categorie);
        recyclerView.setAdapter(searchOwnerAdapter);
    }

    @Override
    public void loadNoPosts() {

    }

}
