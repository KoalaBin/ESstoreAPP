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
import com.fragment.ClothesFragment;
import com.fragment.DrinkFragment;
import com.fragment.FruitFragment;

public class OnSaleActivity extends AppCompatActivity {
    private Button clothes,fruit,drink;
    private ClothesFragment clothesFragment;
    private FruitFragment fruitFragment;
    private DrinkFragment drinkFragment;
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
                fragmentTransaction .hide(fruitFragment)
                                    .hide(drinkFragment)
                                    .show(clothesFragment)
                                    .commit();
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
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
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction .hide(fruitFragment)
                        .hide(clothesFragment)
                        .show(drinkFragment)
                        .commit();
            }
        });
    }

    private void initViews() {
        clothes = findViewById(R.id.clothes);
        fruit = findViewById(R.id.fruit);
        drink = findViewById(R.id.drink);

        clothesFragment = ClothesFragment.newInstance(null,null);
        fruitFragment = FruitFragment.newInstance(null,null);
        drinkFragment = DrinkFragment.newInstance(null,null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction .add(R.id.f2_content,clothesFragment)
                            .add(R.id.f2_content,fruitFragment)
                            .add(R.id.f2_content,drinkFragment)
                            .hide(fruitFragment)
                            .hide(drinkFragment)
                            .commit();

    }


    public class AddProductBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int addProduct = intent.getIntExtra("add_product",-1);
            int outProduct = intent.getIntExtra("out_product",-1);
            if (addProduct == Constants.ADD_CLOTHES || outProduct == Constants.OUT_CLOTHES){
                clothesFragment.updateClothesList();
            }else if (addProduct== Constants.ADD_FRUITS || outProduct == Constants.OUT_FRUITS){
                fruitFragment.updateFruitList();
            }else if (addProduct == Constants.ADD_DRINK || outProduct == Constants.OUT_DRINK){
                drinkFragment.updateDrinkList();
            }

        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(addProductBroadcast);
        super.onDestroy();

    }
}
