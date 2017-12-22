package com.maher.nowhere.ProfileFriendActivity.fragments.photos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.ProfileFriendActivity.adapter.PhotosAdapter;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.mainActivity.fragments.acceuil.AccueilPresenter;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccueilManager;
import com.maher.nowhere.utiles.GridSpacingItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotosFragment extends Fragment implements PhotoView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "viewPager";
    private static final String ARG_PARAM2 = "param2";
    public static final String FROM_PROFILE = "from_profile";
    public static final String FROM_FRIEND_PROFILE = "from_friend_profile";
    private static String from = "";
    private static int FRIEND_ID = 0;

    private Context mContext;
    private View view;
    private RecyclerView recyclerView;


    private ArrayList<Publication> posts;

    private LottieAnimationView lottieAnimationView;


    private OnFragmentInteractionListener mListener;

    public PhotosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotosFragment newInstance(String param1, String param2) {
        PhotosFragment fragment = new PhotosFragment();
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
            from = getArguments().getString("from");
            User user = (User) getArguments().getSerializable("friend_id");
            if (user != null)
                FRIEND_ID = user.getId();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photos, container, false);
        setRetainInstance(true);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        final PhotoPresenter photoPresenter = new PhotoPresenter(this, getActivity());


        if (from != null) {
            if (from.equals(FROM_PROFILE))
                photoPresenter.getListPublication(User.getCurrentUser(getActivity()).getId());
            else if (from.equals(FROM_FRIEND_PROFILE))
                photoPresenter.getListPublication(FRIEND_ID);
        }


        recyclerView = view.findViewById(R.id.rv_photo);

        GridLayoutManager gl = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 4, false));

        PhotosAdapter photosAdapter = new PhotosAdapter(getActivity(), posts);
        recyclerView.setLayoutManager(gl);
        recyclerView.setAdapter(photosAdapter);

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

        posts = new ArrayList<>();
        posts = publications;
        PhotosAdapter photosAdapter = new PhotosAdapter(getActivity(), posts);
        recyclerView.setAdapter(photosAdapter);
    }

    @Override
    public void loadNoPosts(ArrayList<Publication> posts) {
        System.out.println("load No publication");

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
