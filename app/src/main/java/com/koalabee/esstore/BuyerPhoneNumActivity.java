package com.koalabee.esstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.BuyerDao;

import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;

public class BuyerPhoneNumActivity extends AppCompatActivity {
    private ImageButton phoneNumBack;
    private Button phoneNumSave;
    private EditText phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_phone_num);

        initViews();
        initEvents();
    }

    private void initEvents() {
        phoneNumBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //手机号码输入框的默认设置
        Long buyerId = getIntent().getLongExtra("buyerid",-1);
        final BuyerDao buyerDao = MyApplication.getInstances().getDaoSession().getBuyerDao();
        final Buyer buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();
        if (buyer.getPhoneNum() == null)
            phoneNum.setText(null);
        else
            phoneNum.setText(buyer.getPhoneNum());


        phoneNumSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Num = phoneNum.getText().toString();
                buyer.setPhoneNum(Num);
                buyerDao.update(buyer);
                Toast.makeText(BuyerPhoneNumActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initViews() {
        phoneNumBack = findViewById(R.id.img_back);
        phoneNumSave = findViewById(R.id.btn_save);
        phoneNum = findViewById(R.id.et_phonenum);
    }
}
