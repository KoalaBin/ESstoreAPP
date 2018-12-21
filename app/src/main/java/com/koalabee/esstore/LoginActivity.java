package com.koalabee.esstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.AdminDao;
import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Admin;
import com.table.Buyer;
import com.table.Saler;

import org.greenrobot.greendao.query.QueryBuilder;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_account;
    private EditText ed_pwd;
    private Button login;
    private RadioButton buy;
    private RadioButton sale;
    private RadioButton admin;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        initViews();
        initEvents();
    }

    private void initViews() {
        ed_account=findViewById(R.id.account);
        ed_pwd=findViewById(R.id.psd);
        login=findViewById(R.id.login);
        buy=findViewById(R.id.buy);
        sale=findViewById(R.id.sale);
        admin=findViewById(R.id.admin);
    }

    private void initEvents() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admin.isChecked()){
                    String name=ed_account.getText().toString();
                    String password=ed_pwd.getText().toString();

                    DaoSession daoSession= MyApplication.getInstances().getDaoSession();
                    AdminDao adminDao=daoSession.getAdminDao();
                    QueryBuilder<Admin> builder=adminDao.queryBuilder().where(AdminDao.Properties.Name.eq(name),
                    AdminDao.Properties.Password.eq(password));
                    Admin admin=builder.unique();
                    if (admin!=null){
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }else if (sale.isChecked()){
                    String name = ed_account.getText().toString();
                    String password = ed_pwd.getText().toString();

                    DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                    SalerDao salerDao = daoSession.getSalerDao();
                    QueryBuilder<Saler> queryBuilder = salerDao.queryBuilder().where(SalerDao.Properties.Name.eq(name),
                            SalerDao.Properties.Password.eq(password));
                    Saler saler = queryBuilder.unique();
                    if (saler!= null){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,SalerActivity.class);
                        intent.putExtra("salerid",saler.getId());
                        startActivity(intent);
                    }else
                        Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();


                }else if (buy.isChecked()){
                    String name = ed_account.getText().toString();
                    String password = ed_pwd.getText().toString();

                    DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                    BuyerDao buyerDao = daoSession.getBuyerDao();
                    QueryBuilder<Buyer> queryBuilder = buyerDao.queryBuilder().where(BuyerDao.Properties.Name.eq(name),
                            BuyerDao.Properties.Password.eq(password));
                    Buyer buyer = queryBuilder.unique();
                    if (buyer!= null){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,BuyerActivity.class);
                        intent.putExtra("buyerid",buyer.getId());
                        startActivity(intent);
                    }else
                        Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
