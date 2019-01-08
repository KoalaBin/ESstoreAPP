package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.koalabee.esstoreapp.R;


public class BuyerIndexFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View view;
    private Button clothes;
    private Button fruit;
    private Button drink;
    private BuyerClothesFragment buyerClothesFragment;
    private BuyerFruitFragment buyerFruitFragment;
    private BuyerDrinkFragment buyerDrinkFragment;

    public static BuyerIndexFragment newInstance(String param1, String param2) {
        BuyerIndexFragment fragment = new BuyerIndexFragment();
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
       view = inflater.inflate(R.layout.fragment_buyer_index,container,false);
       clothes = view.findViewById(R.id.buyerclothes);
       fruit = view.findViewById(R.id.buyerfruit);
       drink = view.findViewById(R.id.buyerdrink);

        buyerClothesFragment = BuyerClothesFragment.newInstance(null,null);
        buyerFruitFragment = BuyerFruitFragment.newInstance(null,null);
        buyerDrinkFragment = BuyerDrinkFragment.newInstance(null,null);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction .add(R.id.f4_content, buyerClothesFragment)
                .add(R.id.f4_content, buyerFruitFragment)
                .add(R.id.f4_content, buyerDrinkFragment)
                .hide(buyerFruitFragment)
                .hide(buyerDrinkFragment)
                .commit();

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(buyerFruitFragment)
                        .hide(buyerDrinkFragment)
                        .show(buyerClothesFragment)
                        .commit();
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(buyerClothesFragment)
                        .hide(buyerDrinkFragment)
                        .show(buyerFruitFragment)
                        .commit();
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(buyerFruitFragment)
                        .hide(buyerClothesFragment)
                        .show(buyerDrinkFragment)
                        .commit();
            }
        });
       return view;
    }


}
