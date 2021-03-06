package com.maher.nowhere.mainActivity.fragments.acceuil;

import android.content.Context;
import android.content.Intent;
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
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.maher.nowhere.R;
import com.maher.nowhere.commentsActivity.CommentActivity;
import com.maher.nowhere.detailAcceuilActivity.DetailAcceuilActivity;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccueilManager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccueilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccueilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccueilFragment extends Fragment implements AccueilView,AcceuilAdapter.OnButtonsClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "viewPager";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam2;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Publication>posts;

    private AccueilManager accueilManager;
    private LottieAnimationView lottieAnimationView;
    CallbackManager callbackManager;
    ShareDialog shareDialog;


    private OnFragmentInteractionListener mListener;

    public AccueilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccueilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccueilFragment newInstance(String param1, String param2) {
        AccueilFragment fragment = new AccueilFragment();
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

        view=inflater.inflate(R.layout.fragment_accueil, container, false);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        final AccueilPresenter accueilPresenter = new AccueilPresenter(this, getActivity());
        accueilPresenter.getListPublication(User.getCurrentUser(getActivity()).getId());


        recyclerView=view.findViewById(R.id.rv_acceuil);
        posts=new ArrayList<>();

        lm=new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        AcceuilAdapter acceuilAdapter=new AcceuilAdapter(getActivity(),posts,this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(acceuilAdapter);

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
            System.out.println("network error load");

        }

        @Override
        public void loadAllPosts(ArrayList<Publication> publications) {
            System.out.println("load all publications");

            posts=new ArrayList<>();
            posts=publications;
            AcceuilAdapter acceuilAdapter=new AcceuilAdapter(getActivity(),posts,this);
            recyclerView.setAdapter(acceuilAdapter);
        }

        @Override
        public void loadNoPosts(ArrayList<Publication> posts) {
            System.out.println("load No publication");

        }

    @Override
    public void onCommentClick(Publication publication) {

    }

    @Override
    public void onShareClick(Publication publication) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://devellopers.facebook.com/android")).
                            setImageUrl(Uri.parse(publication.getImage()))
                    .build();
            shareDialog.show(linkContent);
        }
    }

    @Override
    public void onLiketClick(Publication publication) {

    }

    @Override
    public void onSendClick(Publication publication) {

    }

    @Override
    public void onItemClick(Publication publication) {
        Intent intent=new Intent(getContext(), DetailAcceuilActivity.class);
        intent.putExtra("publication",publication);
        startActivity(intent);


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
