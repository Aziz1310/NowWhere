package com.maher.nowhere.RestaurantProfileActivity.fragments.menu;

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

import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.adapter.MenuHAdapter;
import com.maher.nowhere.RestaurantProfileActivity.adapter.MenuVAdapter;
import com.maher.nowhere.model.MenuH;
import com.maher.nowhere.model.MenuV;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "viewPager";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;
    private View view;
    private RecyclerView recyclerView_v,recyclerView_h;
    private LinearLayoutManager lmV, lmH;
    private ArrayList<MenuH> menuH;
    private ArrayList<MenuV> menuV;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView_v = view.findViewById(R.id.rv_menuV);
        menuV = new ArrayList<>();
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());
        menuV.add(new MenuV());

        lmV = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        MenuVAdapter menuVAdapter = new MenuVAdapter(getActivity(), menuV);
        recyclerView_v.setLayoutManager(lmV);
        recyclerView_v.setAdapter(menuVAdapter);

        recyclerView_h = view.findViewById(R.id.rv_menuH);
        menuH = new ArrayList<>();
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());
        menuH.add(new MenuH());

        lmH = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);
        MenuHAdapter menuHAdapter = new MenuHAdapter(getActivity(), menuH);
        recyclerView_h.setLayoutManager(lmH);
        recyclerView_h.setAdapter(menuHAdapter);

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
