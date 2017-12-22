package com.maher.nowhere.ContactsActivity.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.ContactsActivity.adapters.SuggestionsAdapter;
import com.maher.nowhere.ContactsActivity.presenters.SuggestionPresenter;
import com.maher.nowhere.ContactsActivity.views.SuggestionView;
import com.maher.nowhere.R;
import com.maher.nowhere.login.LoginPresenter;
import com.maher.nowhere.model.Suggestions;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.AccountManager;
import com.maher.nowhere.providers.ContactsManager;

import java.util.ArrayList;


public class SuggestionsFragment extends Fragment implements SuggestionView,SuggestionsAdapter.OnSendInvitListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<User> suggestionses;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;

    private ContactsManager contactsManager;
    private LottieAnimationView lottieAnimationView;



    public SuggestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuggestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuggestionsFragment newInstance(String param1, String param2) {
        SuggestionsFragment fragment = new SuggestionsFragment();
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
        view = inflater.inflate(R.layout.fragment_suggestions, container, false);
        lottieAnimationView=(LottieAnimationView)view.findViewById(R.id.loadingAnimation);

        final SuggestionPresenter suggestionPresenter=new SuggestionPresenter(this,getActivity());
        suggestionPresenter.getListSuggestion(User.getCurrentUser(getActivity()).getId());

        suggestionses = new ArrayList<>();
        SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getActivity(), suggestionses,this);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_suggestion);
        lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(suggestionsAdapter);
        recyclerView.setLayoutManager(lm);

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
    public void sendInvitation() {
    }

    @Override
    public void networkError() {

    }

    @Override
    public void loadAllSuggestion(ArrayList<User> users) {
        suggestionses = new ArrayList<>();
        suggestionses=users;
        SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(getActivity(), suggestionses,this);
        recyclerView.setAdapter(suggestionsAdapter);
    }

    @Override
    public void loadNoSuggestion(ArrayList<User> users) {

    }

    @Override
    public void invitationSendSuccess() {
        System.out.println("invitation success");
    }

    @Override
    public void invitationSendError() {
        System.out.println("invitation error");

    }

    @Override
    public void onSendBtnClick(User user) {
        final SuggestionPresenter suggestionPresenter=new SuggestionPresenter(this,getActivity());
        suggestionPresenter.sendInvitation(User.getCurrentUser(getActivity()).getId(),user.getId());

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
