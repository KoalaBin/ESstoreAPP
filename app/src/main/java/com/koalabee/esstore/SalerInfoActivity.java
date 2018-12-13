package com.koalabee.esstore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Saler;

import de.hdodenhof.circleimageview.CircleImageView;

public class SalerInfoActivity extends AppCompatActivity {

    private Button salerInfoOk;
    private CircleImageView salerinfoPic;
    private EditText salerInfoName, salerInfoPassowrd, salerInfoPhoneNum, salerInfoAddresss, salerInfoUqPwd;
    private RadioButton salerInfoMan, salerInfoWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saler_info);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        initViews();
        initEvents();
    }

    private void initEvents() {
        Long salerid = getIntent().getLongExtra("salerid",-1);
        SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
        Saler saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(salerid)).unique();
        Bitmap bitmap = BitmapFactory.decodeFile(saler.getPicPath());
        salerinfoPic.setImageBitmap(bitmap);
        salerInfoName.setText(saler.getName());
        salerInfoPassowrd.setText(saler.getPassword());

        if (saler.getPhoneNum() == null)
            salerInfoPhoneNum.setText(null);
        else
            salerInfoPhoneNum.setText(saler.getPhoneNum());

        if (saler.getAddress() == null)
            salerInfoAddresss.setText(null);
        else
            salerInfoAddresss.setText(saler.getAddress());

        salerInfoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String salername = salerInfoName.getText().toString();
                String salerpwd = salerInfoPassowrd.getText().toString();
                String saleruqpwd = salerInfoUqPwd.getText().toString();
                String salerphone = salerInfoPhoneNum.getText().toString();
                String saleraddress = salerInfoAddresss.getText().toString();

                SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
                Saler saler = new Saler();

                if (salername == null || salerpwd == null || saleruqpwd == null)
                    Toast.makeText(SalerInfoActivity.this,"用户名、密码或确认密码不能为空！",Toast.LENGTH_SHORT).show();
                else if (salerpwd != saleruqpwd)
                    Toast.makeText(SalerInfoActivity.this,"两次密码输入不一致！",Toast.LENGTH_SHORT).show();
                 else{
                     saler.setName(salername);
                     saler.setPassword(salerpwd);
                     if (salerInfoMan.isChecked())
                         saler.setGender("男");
                     else if (salerInfoWoman.isChecked())
                         saler.setGender("女");
                     else
                         saler.setGender(null);
                     saler.setPhoneNum(salerphone);
                     saler.setAddress(saleraddress);
                     salerDao.update(saler);
                     Toast.makeText(SalerInfoActivity.this,"信息修改成功！",Toast.LENGTH_SHORT).show();
                     finish();
                }

            }
        });
    }

    private void initViews() {
        salerInfoOk = findViewById(R.id.btn_salerinfo_ok);
        salerinfoPic = findViewById(R.id.salerinfo_pic);
        salerInfoName = findViewById(R.id.et_salerinfo_name);
        salerInfoPassowrd = findViewById(R.id.et_salerinfo_password);
        salerInfoUqPwd = findViewById(R.id.et_salerinfo_uqpwd);
        salerInfoPhoneNum = findViewById(R.id.et_salerinfo_phonenum);
        salerInfoAddresss = findViewById(R.id.et_salerinfo_address);
        salerInfoMan = findViewById(R.id.salerinfo_man);
        salerInfoWoman = findViewById(R.id.salerinfo_woman);
    }


}
