package com.koalabee.esstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Constants;
import com.example.koalabee.esstoreapp.R;
import com.fragment.SalerClothesFragment;
import com.fragment.SalerDrinkFragment;
import com.fragment.SalerFruitFragment;

public class OnSaleActivity extends AppCompatActivity {
    private Button clothes,fruit,drink;
    private SalerClothesFragment salerClothesFragment;
    private SalerFruitFragment salerFruitFragment;
    private SalerDrinkFragment salerDrinkFragment;
    private AddProductBroadcast addProductBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_sale);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initViews();
        initEvents();

        IntentFilter intentFilter = new IntentFilter(Constants.PRODUCT_EVENT);
        addProductBroadcast = new AddProductBroadcast();
        registerReceiver(addProductBroadcast,intentFilter);
    }

    private void initEvents() {
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(salerFruitFragment)
                                    .hide(salerDrinkFragment)
                                    .show(salerClothesFragment)
                                    .commit();
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(salerClothesFragment)
                        .hide(salerDrinkFragment)
                        .show(salerFruitFragment)
                        .commit();
            }
        });

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(salerFruitFragment)
                        .hide(salerClothesFragment)
                        .show(salerDrinkFragment)
                        .commit();
            }
        });
    }

    private void initViews() {
        clothes = findViewById(R.id.clothes);
        fruit = findViewById(R.id.fruit);
        drink = findViewById(R.id.drink);

        salerClothesFragment = SalerClothesFragment.newInstance(null,null);
        salerFruitFragment = SalerFruitFragment.newInstance(null,null);
        salerDrinkFragment = SalerDrinkFragment.newInstance(null,null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction .add(R.id.f2_content, salerClothesFragment)
                            .add(R.id.f2_content, salerFruitFragment)
                            .add(R.id.f2_content, salerDrinkFragment)
                            .hide(salerFruitFragment)
                            .hide(salerDrinkFragment)
                            .commit();

    }


    public class AddProductBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int addProduct = intent.getIntExtra("add_product",-1);
            int outProduct = intent.getIntExtra("out_product",-1);
            if (addProduct == Constants.ADD_CLOTHES || outProduct == Constants.OUT_CLOTHES){
                salerClothesFragment.updateClothesList();
            }else if (addProduct== Constants.ADD_FRUITS || outProduct == Constants.OUT_FRUITS){
                salerFruitFragment.updateFruitList();
            }else if (addProduct == Constants.ADD_DRINK || outProduct == Constants.OUT_DRINK){
                salerDrinkFragment.updateDrinkList();
            }

        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(addProductBroadcast);
        super.onDestroy();

    }
}
