package com.koalabee.esstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.MyApplication;
import com.UserClass;
import com.adapter.SalerAdapter;
import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Saler;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SalerActivity extends AppCompatActivity {
    private List<UserClass> salerClasses = new ArrayList<>();
    private SalerAdapter salerAdapter;
    private ListView listView;
    private FloatingActionButton fabtnOnsale;
    private CircleImageView salerheadpic;
    private TextView salerName;
    private Long salerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saler);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        initViews();
        initEvents();
        initSalerClass();
        initHeadpicName();

        salerAdapter = new SalerAdapter(this,R.layout.saler_item, salerClasses);
        listView.setAdapter(salerAdapter);

    }
    //初始化头像和昵称
    private void initHeadpicName() {
        salerId = getIntent().getLongExtra("salerid",-1);
        SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
        Saler saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(salerId)).unique();
        if(saler.getPicPath() == null){
            salerName.setText(saler.getName());
            salerheadpic.setImageResource(R.mipmap.ic_launcher);
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(saler.getPicPath());
            salerName.setText(saler.getName());
            salerheadpic.setImageBitmap(bitmap);
        }

    }

    private void initViews() {
        listView = findViewById(R.id.lv_salerclass);
        fabtnOnsale = findViewById(R.id.fabtn_onsale);
        salerheadpic = findViewById(R.id.salerheadpic);
        salerName = findViewById(R.id.salername);
    }

    private void initEvents() {
        fabtnOnsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalerActivity.this,ProductActivity.class);
                intent.putExtra("salerid",salerId);
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
                        break;
                    //case 1:
                    case 2:
                        Long salerid = getIntent().getLongExtra("salerid",-1);
                        Intent salerinfo = new Intent(SalerActivity.this,SalerInfoActivity.class);
                        salerinfo.putExtra("salerid",salerid);
                        startActivity(salerinfo);


                }

            }
        });
    }
    //初始化卖家选项菜单
    private void initSalerClass() {
        UserClass onSale = new UserClass("已上架商品",null);
        salerClasses.add(onSale);
        UserClass saleOrder = new UserClass("销售订单",null);
        salerClasses.add(saleOrder);
        UserClass saleData = new UserClass("修改信息",null);
        salerClasses.add(saleData);
    }
}
