package com.koalabee.esstore;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.SalerAdapter;
import com.example.koalabee.esstoreapp.R;

import java.util.ArrayList;
import java.util.List;

public class SalerActivity extends AppCompatActivity {
    private List<SalerClass> salerClasses = new ArrayList<>();
    private SalerAdapter salerAdapter;
    private ListView listView;
    private FloatingActionButton fabtnOnsale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saler);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }


        salerAdapter = new SalerAdapter(this,R.layout.saler_item,salerClasses);
        listView = findViewById(R.id.lv_salerclass);
        listView.setAdapter(salerAdapter);
        initViews();
        initEvents();
        initSalerClass();
    }

    private void initViews() {
        fabtnOnsale = findViewById(R.id.fabtn_onsale);
    }

    private void initEvents() {
        fabtnOnsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalerActivity.this,ProductActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(SalerActivity.this,OnSaleActivity.class);
                        startActivity(intent);
                }
            }
        });
    }

    private void initSalerClass() {
        SalerClass onSale = new SalerClass("已上架商品",1);
        salerClasses.add(onSale);
        SalerClass saleOrder = new SalerClass("销售订单",1);
        salerClasses.add(saleOrder);
        SalerClass saleData = new SalerClass("修改信息",1);
        salerClasses.add(saleData);
    }
}
