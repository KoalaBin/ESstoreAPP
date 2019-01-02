package com.koalabee.esstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.BuyerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;

public class BuyerAddressActivity extends AppCompatActivity {
    private ImageButton addressBack;
    private Button addressSave;
    private EditText address;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_address);

        initViews();
        initEvents();
    }

    private void initEvents() {

        title.setText("我的收货地址");

        addressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //收货地址输入框的默认设置
        Long buyerId = getIntent().getLongExtra("buyerid",-1);
        final BuyerDao buyerDao = MyApplication.getInstances().getDaoSession().getBuyerDao();
        final Buyer buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();
        if (buyer.getAddress() == null)
            address.setText(null);
        else
            address.setText(buyer.getAddress());

        addressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ass = address.getText().toString();
                buyer.setAddress(ass);
                buyerDao.update(buyer);
                Toast.makeText(BuyerAddressActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initViews() {
        addressBack = findViewById(R.id.img_back);
        addressSave = findViewById(R.id.btn_save);
        address = findViewById(R.id.et_address);
        title = findViewById(R.id.title_buyerinfo);
    }
}
