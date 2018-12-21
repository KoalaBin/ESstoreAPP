package com.fragment;

import android.content.Context;
import android.net.Uri;
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
    private ClothesFragment clothesFragment;
    private FruitFragment fruitFragment;
    private DrinkFragment drinkFragment;

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

        clothesFragment = ClothesFragment.newInstance(null,null);
        fruitFragment = FruitFragment.newInstance(null,null);
        drinkFragment = DrinkFragment.newInstance(null,null);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction .add(R.id.f4_content,clothesFragment)
                .add(R.id.f4_content,fruitFragment)
                .add(R.id.f4_content,drinkFragment)
                .hide(fruitFragment)
                .hide(drinkFragment)
                .commit();

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(fruitFragment)
                        .hide(drinkFragment)
                        .show(clothesFragment)
                        .commit();
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(clothesFragment)
                        .hide(drinkFragment)
                        .show(fruitFragment)
                        .commit();
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(fruitFragment)
                        .hide(clothesFragment)
                        .show(drinkFragment)
                        .commit();
            }
        });
       return view;
    }


}
