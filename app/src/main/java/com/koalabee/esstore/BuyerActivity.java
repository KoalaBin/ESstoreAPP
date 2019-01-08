package com.koalabee.esstore;
;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.koalabee.esstoreapp.R;
import com.fragment.BuyerIndexFragment;
import com.fragment.PersonalBuyerFragment;
import com.fragment.ShopCarFragment;

public class BuyerActivity extends AppCompatActivity {
    private ImageButton index;
    private ImageButton shopcar;
    private ImageButton personal;

    private BuyerIndexFragment buyerIndexFragment;
    private ShopCarFragment shopCarFragment;
    private PersonalBuyerFragment personalBuyerFragment;
    public Long buyerid;

    public Long getBuyerid() {
        return buyerid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        initViews();
        initEvents();
        buyerid = getIntent().getLongExtra("buyerid",-1);
    }

    private void initEvents() {
        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(shopCarFragment)
                        .hide(personalBuyerFragment)
                        .show(buyerIndexFragment)
                        .commit();
            }
        });

        shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(buyerIndexFragment)
                        .hide(personalBuyerFragment)
                        .show(shopCarFragment)
                        .commit();
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(buyerIndexFragment)
                        .hide(shopCarFragment)
                        .show(personalBuyerFragment)
                        .commit();
            }
        });

    }

    private void initViews() {
        index = findViewById(R.id.btn_index);
        shopcar = findViewById(R.id.btn_shopcar);
        personal = findViewById(R.id.btn_personal);

        buyerIndexFragment = BuyerIndexFragment.newInstance(null,null);
        shopCarFragment = ShopCarFragment.newInstance(null,null);
        personalBuyerFragment = PersonalBuyerFragment.newInstance(null,null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction .add(R.id.f3_content,buyerIndexFragment)
                            .add(R.id.f3_content,shopCarFragment)
                            .add(R.id.f3_content,personalBuyerFragment)
                            .hide(shopCarFragment)
                            .hide(personalBuyerFragment)
                            .commit();
    }
}
