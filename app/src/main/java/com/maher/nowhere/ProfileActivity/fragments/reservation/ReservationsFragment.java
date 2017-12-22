package com.maher.nowhere.ProfileActivity.fragments.reservation;

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
import com.android.volley.VolleyError;
import com.maher.nowhere.ProfileActivity.adapter.ReservationAdapter;
import com.maher.nowhere.ProfileFriendActivity.adapter.PhotosAdapter;
import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotoPresenter;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Reservation;
import com.maher.nowhere.model.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReservationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservationsFragment extends Fragment implements ReservationView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Reservation> reservations;
    private LottieAnimationView lottieAnimationView;




    private OnFragmentInteractionListener mListener;

    public ReservationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservationsFragment newInstance(String param1, String param2) {
        ReservationsFragment fragment = new ReservationsFragment();
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
        View view= inflater.inflate(R.layout.fragment_reservations, container, false);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        final ReservationPresenter reservationPresenter = new ReservationPresenter(this, getActivity());
        reservationPresenter.getListReservation(User.getCurrentUser(getActivity()).getId());
        setRetainInstance(true);



        recyclerView=view.findViewById(R.id.rv_acceuil);
        reservations=new ArrayList<>();
/*        reservations.add(new Reservation("RSV409","19/02/2017",true));
        reservations.add(new Reservation("RSV209","21/02/2017",true));
        reservations.add(new Reservation("RSV408","01/02/2017",true));
        reservations.add(new Reservation("RSV411","19/12/2017",false));
        reservations.add(new Reservation("RSV202","19/02/2017",true));
        reservations.add(new Reservation("RSV409","19/02/2017",false));*/

        lm=new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        ReservationAdapter reservationAdapter=new ReservationAdapter(getActivity(),reservations);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(reservationAdapter);

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

    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void networkError(VolleyError error) {
        System.out.println("network error load reservation fragment "+error.toString());

    }

    @Override
    public void loadAllReservation(ArrayList<Reservation> reservations) {

        this.reservations=new ArrayList<>();
        this.reservations=reservations;
        ReservationAdapter reservationAdapter=new ReservationAdapter(getActivity(),reservations);
        recyclerView.setAdapter(reservationAdapter);
    }

    @Override
    public void loadNoReservation(ArrayList<Reservation> reservations) {
        System.out.println("load No Reservation");

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
