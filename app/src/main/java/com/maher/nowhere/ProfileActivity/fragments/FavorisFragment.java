package com.maher.nowhere.ProfileActivity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.adapter.SearchAdapter;
import com.maher.nowhere.model.Search;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavorisFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavorisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Search> lsearch;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;


    private OnFragmentInteractionListener mListener;

    public FavorisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavorisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorisFragment newInstance(String param1, String param2) {
        FavorisFragment fragment = new FavorisFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_favoris, container, false);
        lsearch = new ArrayList<>();
        lsearch.add(new Search(R.drawable.image,"23","09","2017","BYE BYE SUMMER", "Carpe Dieam"));
        lsearch.add(new Search(R.drawable.img3,"11","09","2017","FIESTA GITANA", "Yuka"));
        lsearch.add(new Search(R.drawable.img1,"22","09","2017","SABRI MOSBAH", "Sabri"));
        lsearch.add(new Search(R.drawable.img2,"31","2","2017","SABRI MOSBAH", "Sabri"));
        lsearch.add(new Search(R.drawable.img4,"19","09","2017","SABRI MOSBAH", "Sabri"));

        recyclerView=(RecyclerView)view.findViewById(R.id.rv_search);
        lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        SearchAdapter searchAdapter = new SearchAdapter(getActivity(), lsearch,"b");
        recyclerView.setAdapter(searchAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
