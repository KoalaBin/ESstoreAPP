package com.koalabee.esstore;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.support.v4.app.FragmentManager;

import com.adapter.BuyerListAdapter;
import com.example.koalabee.esstoreapp.R;
import com.fragment.AddUserFragment;
import com.fragment.BuyerListFragment;
import com.fragment.SalerListFragment;

public class AdminActivity extends AppCompatActivity {
    public static final String ADD_USER_SUCCESS = "com.koalabee.esstore.AdminActivity.add_user_success";
    public static final int ADD_BUYER = 1,ADD_SALER = 2;
    private Button btnBuyer,btnSaler,btnAdmin;
    private BuyerListFragment buyerListFragment;
    private SalerListFragment salerListFragment;
    private AddUserFragment addUserFragment;
    private AddUserBroadcastReceiver addUserBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
        actionBar.hide();
        }
        initViews();
        initEvents();
        IntentFilter intentFilter = new IntentFilter(ADD_USER_SUCCESS);
        addUserBroadcastReceiver = new AddUserBroadcastReceiver();
        registerReceiver(addUserBroadcastReceiver,intentFilter);
    }

    private void initEvents() {
        btnBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(salerListFragment)
                           .hide(addUserFragment)
                           .show(buyerListFragment)
                           .commit();
            }
        });

        btnSaler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(buyerListFragment)
                        .hide(addUserFragment)
                        .show(salerListFragment)
                        .commit();
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(salerListFragment)
                        .hide(buyerListFragment)
                        .show(addUserFragment)
                        .commit();
            }
        });
    }

    private void initViews() {
        btnBuyer = findViewById(R.id.btn_buyer);
        btnSaler = findViewById(R.id.btn_saler);
        btnAdmin = findViewById(R.id.btn_admin);

        buyerListFragment = BuyerListFragment.newInstance(null,null);
        salerListFragment = SalerListFragment.newInstance(null,null);
        addUserFragment = AddUserFragment.newInstance(null,null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.f1_content,buyerListFragment)
                .add(R.id.f1_content,salerListFragment)
                .add(R.id.f1_content,addUserFragment)
                .hide(salerListFragment)
                .hide(addUserFragment)
                .commit();


    }

    public class AddUserBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int userType = intent.getIntExtra("user_type",-1);
            if (userType == ADD_BUYER){
                buyerListFragment.updateBuyerList();
            }else if (userType == ADD_SALER){
                salerListFragment.updateSalerList();
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(addUserBroadcastReceiver);
        super.onDestroy();
    }
}
