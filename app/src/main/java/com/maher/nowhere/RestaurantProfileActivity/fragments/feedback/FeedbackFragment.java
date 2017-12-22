package com.maher.nowhere.RestaurantProfileActivity.fragments.feedback;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.adapter.FeedbackAdapter;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class FeedbackFragment extends Fragment implements FeedbackView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Feedback> feedbacks;
    private Owner owner;
    private LottieAnimationView lottieAnimationView;


    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
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
            owner = (Owner) getArguments().getSerializable("owner");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);
        CircleImageView profile_img=view.findViewById(R.id.profile_img);
        Picasso.with(getActivity()).load(Uri.parse(Urls.IMG_URL_USER +User.getCurrentUser(getActivity()).getImage())).into(profile_img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }
            @Override
            public void onError() {
            }
        });
        ImageView btnComment=view.findViewById(R.id.btnComment);
        final EditText etcomment=view.findViewById(R.id.etAvis);

        recyclerView = view.findViewById(R.id.rv_feedback);
        feedbacks=new ArrayList<>();
        lm = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        final FeedbackAdapter feedbackAdapter = new FeedbackAdapter(getActivity(), feedbacks);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(feedbackAdapter);

        if (owner != null) {
            final FeedbackPresenter feedbackPresenter = new FeedbackPresenter(this, getActivity());
            feedbackPresenter.getListFeedback(User.getCurrentUser(getActivity()).getId(), owner.getId());
            btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String contenu =etcomment.getText().toString();
                    feedbackPresenter.addFeedback(User.getCurrentUser(getActivity()).getId(),
                            owner.getId(),
                            contenu,
                            "4");
                }
            });

        }


        return view;
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
    public void loadAllFeedbacks(ArrayList<Feedback> feedbacks) {
        System.out.println("load all feedbacks");

        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(getActivity(), feedbacks);
        recyclerView.setAdapter(feedbackAdapter);
    }

    @Override
    public void loadNoFeedbacks(ArrayList<Feedback> feedbacks) {
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(getActivity(), feedbacks);
        recyclerView.setAdapter(feedbackAdapter);

    }

    @Override
    public void addFeedbackSuccess(String message) {
        System.out.println("add feedback success");
        final FeedbackPresenter feedbackPresenter = new FeedbackPresenter(this, getActivity());
        feedbackPresenter.getListFeedback(User.getCurrentUser(getActivity()).getId(), owner.getId());
    }

    @Override
    public void addFeedbackError(String message) {
        System.out.println("add feedback error");
    }
}
