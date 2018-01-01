package com.maher.nowhere.CentreActivity.fragments.products;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.CentreActivity.adapter.ProductsAdapter;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.Publication;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment implements ProduitView{
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
    private ArrayList<Product> products;

    private OnFragmentInteractionListener mListener;
    private LottieAnimationView lottieAnimationView;
    private Owner owner;
    private String categorie;


    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
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
            owner=(Owner)getArguments().getSerializable("owner");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_products, container, false);
        setRetainInstance(true);
        products = new ArrayList<>();
        lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.loadingAnimation);

        ProductsPresenter productsPresenter=new ProductsPresenter(this,getActivity());
        productsPresenter.getListProduit(owner.getId());


        ProductsAdapter productsAdapter = new ProductsAdapter(getActivity(), products);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_product);
        lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setLayoutManager(lm);

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
    public void loadAllProducts(ArrayList<Product> products) {
        System.out.println("load all publications");

        this.products=new ArrayList<>();
        this.products=products;
        ProductsAdapter productsAdapter=new ProductsAdapter(getActivity(),products);
        recyclerView.setAdapter(productsAdapter);
    }

    @Override
    public void loadNoProduct(ArrayList<Product> products) {
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
