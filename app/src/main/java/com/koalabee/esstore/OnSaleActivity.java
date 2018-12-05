package com.koalabee.esstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.koalabee.esstoreapp.R;
import com.fragment.ClothesFragment;
import com.fragment.DrinkFragment;
import com.fragment.FruitFragment;

public class OnSaleActivity extends AppCompatActivity {
    private Button clothes,fruit,drink;
    private ClothesFragment clothesFragment;
    private FruitFragment fruitFragment;
    private DrinkFragment drinkFragment;

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
    }

    private void initEvents() {
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews() {
        clothes = findViewById(R.id.clothes);
        fruit = findViewById(R.id.fruit);
        drink = findViewById(R.id.drink);
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
