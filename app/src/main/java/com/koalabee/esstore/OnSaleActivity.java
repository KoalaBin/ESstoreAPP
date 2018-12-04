package com.koalabee.esstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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

    public class AddProductBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int userType = intent.getIntExtra("user_type",-1);
            if (userType == ProductActivity.ADD_PRODUCT){

            }

        }
    }
}
