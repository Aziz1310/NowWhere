package com.maher.nowhere.RestaurantProfileActivity.fragments.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.adapter.FeedbackAdapter;
import com.maher.nowhere.RestaurantProfileActivity.adapter.MenuHAdapter;
import com.maher.nowhere.RestaurantProfileActivity.adapter.MenuVAdapter;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Menu;
import com.maher.nowhere.model.MenuH;
import com.maher.nowhere.model.MenuV;
import com.maher.nowhere.model.Owner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements MenuView, MenuHAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "viewPager";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;
    private View view;
    private RecyclerView recyclerView_v, recyclerView_h;
    private LinearLayoutManager lmV, lmH;
    private ArrayList<Menu> menuH;
    private Owner owner;
    private LottieAnimationView lottieAnimationView;

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
            owner = (Owner) getArguments().getSerializable("owner");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        setRetainInstance(true);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);

        recyclerView_v = view.findViewById(R.id.rv_menuV);
        lmV = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        recyclerView_v.setLayoutManager(lmV);

        recyclerView_h = view.findViewById(R.id.rv_menuH);
        menuH = new ArrayList<>();

        lmH = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        MenuHAdapter menuHAdapter = new MenuHAdapter(getActivity(), menuH, this);
        recyclerView_h.setLayoutManager(lmH);
        recyclerView_h.setAdapter(menuHAdapter);

        MenuPresenter menuPresenter = new MenuPresenter(this, getActivity());
        menuPresenter.getListMenus(owner.getId());

        AppCompatButton btnCommander = view.findViewById(R.id.btn_commander);
        btnCommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", owner.getTelFix(), null));
                    startActivity(intent);
                } catch (Exception ignore) {
                }
            }
        });

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
    public void networkError() {
    }


    @Override
    public void loadAllMenus(ArrayList<Menu> menus) {
        System.out.println("load all menus");
        menuH = new ArrayList<>();
        menuH = menus;
        MenuHAdapter menuHAdapter = new MenuHAdapter(getActivity(), menuH, this);
        recyclerView_h.setAdapter(menuHAdapter);

        MenuVAdapter menuVAdapter = new MenuVAdapter(getActivity(), menuH.get(0).getSubMenus());
        recyclerView_v.setLayoutManager(lmV);
        recyclerView_v.setAdapter(menuVAdapter);

    }

    @Override
    public void loadNoMenu(ArrayList<Menu> menus) {
        menuH = new ArrayList<>();
        menuH = menus;
        MenuHAdapter menuHAdapter = new MenuHAdapter(getActivity(), menuH, this);
        recyclerView_h.setAdapter(menuHAdapter);
    }

    @Override
    public void onClickListener(int postition) {
        MenuVAdapter menuVAdapter = new MenuVAdapter(getActivity(), menuH.get(postition).getSubMenus());
        recyclerView_v.setLayoutManager(lmV);
        recyclerView_v.setAdapter(menuVAdapter);

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
