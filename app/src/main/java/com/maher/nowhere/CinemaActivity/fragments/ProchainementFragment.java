package com.maher.nowhere.CinemaActivity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.CinemaActivity.FilmPresenter;
import com.maher.nowhere.CinemaActivity.FilmView;
import com.maher.nowhere.CinemaActivity.adapter.EnSalleAdapter;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Film;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProchainementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProchainementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProchainementFragment extends Fragment implements FilmView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Film> lsearch;

    private OnFragmentInteractionListener mListener;
    private LottieAnimationView lottieAnimationView;

    public ProchainementFragment() {
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
    public static ProchainementFragment newInstance(String param1, String param2) {
        ProchainementFragment fragment = new ProchainementFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_en_salle, container, false);


        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        final FilmPresenter filmPresenter = new FilmPresenter(this, getActivity());
        filmPresenter.getListPublication(0);

        recyclerView=view.findViewById(R.id.rv_enSalle);
        lsearch = new ArrayList<>();

        lm=new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        EnSalleAdapter enSalleAdapter = new EnSalleAdapter(getActivity(), lsearch);
        recyclerView.setAdapter(enSalleAdapter);

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

        @Override
        public void showProgress() {
            lottieAnimationView.setVisibility(View.VISIBLE);

        }

        @Override
        public void hideProgress() {
            lottieAnimationView.setVisibility(View.GONE);

        }

        @Override
        public void networkError() {
            System.out.println("network error load");

        }

        @Override
        public void loadAllFilms(ArrayList<Film> films) {
            System.out.println("load all films");

            lsearch=new ArrayList<>();
            lsearch=films;
            EnSalleAdapter enSalleAdapter=new EnSalleAdapter(getActivity(),lsearch);
            recyclerView.setAdapter(enSalleAdapter);
        }

        @Override
        public void loadNoFilm(ArrayList<Film> films) {
            System.out.println("load No film");

           }
}
