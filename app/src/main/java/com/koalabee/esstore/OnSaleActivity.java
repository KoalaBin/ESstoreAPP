package com.koalabee.esstore;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.koalabee.esstoreapp.R;

public class OnSaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_sale);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }
}
