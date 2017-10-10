package com.maher.nowhere.ContactsActivity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maher.nowhere.R;
import com.maher.nowhere.ContactsActivity.adapters.AmisAdapter;
import com.maher.nowhere.model.Friend;

import java.util.ArrayList;

public class AmisFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Friend> amis;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;



    public AmisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AmisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AmisFragment newInstance(String param1, String param2) {
        AmisFragment fragment = new AmisFragment();
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
        view= inflater.inflate(R.layout.fragment_amis, container, false);
        amis = new ArrayList<>();
        amis.add(new Friend(R.drawable.profile_image,"Lili","Last seen 12 minutes ago"));
        amis.add(new Friend(R.drawable.profile_image,"Bilel D","Online"));
        amis.add(new Friend(R.drawable.profile_image,"Oumaima H","Offline"));
        amis.add(new Friend(R.drawable.profile_image,"Selima T","Offline"));
        amis.add(new Friend(R.drawable.profile_image,"Intissar S","Online"));
        amis.add(new Friend(R.drawable.profile_image,"Lassaad","Online"));

        AmisAdapter amisAdapter=new AmisAdapter(getActivity(),amis);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_amis);
        lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(amisAdapter);
        recyclerView.setLayoutManager(lm);

        return view;
    }



}
