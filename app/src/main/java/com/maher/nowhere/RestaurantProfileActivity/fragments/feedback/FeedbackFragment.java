package com.maher.nowhere.RestaurantProfileActivity.fragments.feedback;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;

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
    private String rating="3";


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
        setRetainInstance(true);
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
                            rating);
                }
            });

        }
        final RatingBar rb_resto2=view.findViewById(R.id.rb_resto2);
        rb_resto2.setRating(3.5f);
        rb_resto2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = rb_resto2.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int)starsf + 1;
                    rb_resto2.setRating(stars);

                    rating=stars+"";
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }




                return true;
            }});




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
        RatingBar rb_resto=view.findViewById(R.id.rb_resto);
        rb_resto.setRating(Float.parseFloat(feedbacks.get(0).getGlobalNote()));
        TextView feedbackNumber=view.findViewById(R.id.feedback_nbr);
        feedbackNumber.setText(String.format(Locale.FRANCE,"%d avis", feedbacks.size()));
        TextView tvAvis=view.findViewById(R.id.tv_avis);
        tvAvis.setText(String.format("%s", Float.parseFloat(feedbacks.get(0).getGlobalNote())));

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
        final EditText etcomment=view.findViewById(R.id.etAvis);
        etcomment.setText("");
        final FeedbackPresenter feedbackPresenter = new FeedbackPresenter(this, getActivity());
        feedbackPresenter.getListFeedback(User.getCurrentUser(getActivity()).getId(), owner.getId());
    }

    @Override
    public void addFeedbackError(String message) {
        System.out.println("add feedback error");
        Toast.makeText(getActivity(),"Votre avis ne peut pas être ajoutée", Toast.LENGTH_SHORT).show();

    }
}
