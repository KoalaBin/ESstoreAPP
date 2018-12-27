package com.koalabee.esstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.BuyerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;

public class BuyerGenderActivity extends AppCompatActivity {
    private ImageButton genderBack;
    private Button genderSave;
    private TextView genderTitle;
    private RadioButton male;
    private RadioButton female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_gender);

        initViews();
        initEvents();
    }

    private void initEvents() {

        genderTitle.setText("性别");

        genderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //性别默认设置
        Long buyerId = getIntent().getLongExtra("buyerid",-1);
        final BuyerDao buyerDao = MyApplication.getInstances().getDaoSession().getBuyerDao();
        final Buyer buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();
        if (buyer.getGender() == null){
            male.setChecked(false);
            female.setChecked(false);
        } else if (buyer.getGender() == "男"){
            male.setChecked(true);
            female.setChecked(false);
        } else if (buyer.getGender() == "女"){
            male.setChecked(false);
            female.setChecked(true);
        }


        genderSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (male.isChecked()){
                   buyer.setGender("男");
                   buyerDao.update(buyer);
                   Toast.makeText(BuyerGenderActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                   finish();
               }else if (female.isChecked()){
                   buyer.setGender("女");
                   buyerDao.update(buyer);
                   Toast.makeText(BuyerGenderActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                   finish();
               }else
                   Toast.makeText(BuyerGenderActivity.this,"信息不完整，请重新填写",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {
        genderBack = findViewById(R.id.img_back);
        genderSave = findViewById(R.id.btn_save);
        genderTitle = findViewById(R.id.title_buyerinfo);
        male = findViewById(R.id.rb_male);
        female = findViewById(R.id.rb_female);
    }
}
