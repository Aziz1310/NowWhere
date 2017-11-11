package com.maher.nowhere.mainActivity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.helpers.JsonToObjectParser;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.TinderCard;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.json.JSONArray;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeeklikFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeeklikFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklikFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    ArrayList<Post> posts;
    private SwipePlaceHolderView mSwipeView;
    private OnFragmentInteractionListener mListener;
    private int nbr = 0;

    public WeeklikFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WeeklikFragment newInstance(ArrayList<Post> posts) {
        WeeklikFragment fragment = new WeeklikFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, posts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //  posts = (ArrayList<Post>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_weeklik, container, false);
        setRetainInstance(true);

        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        getAllPosts();

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

    private void getAllPosts() {
        EventManager eventManager = new EventManager(getActivity());
        eventManager.getPosts(new VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                posts = new JsonToObjectParser().parsePosts((JSONArray) response);
                for (Post post : posts) {
                    mSwipeView.addView(new TinderCard(getActivity(), post, mSwipeView));
                }
                view.findViewById(R.id.btnDislike).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSwipeView.doSwipe(false);

                    }
                });

                view.findViewById(R.id.btnLike).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSwipeView.doSwipe(true);

                    }
                });

            }

            @Override
            public void onError(Object error) {

            }
        });
    }
}
