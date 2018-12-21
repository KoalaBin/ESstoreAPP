package com.koalabee.esstore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.SalerDao;
import com.bumptech.glide.Glide;
import com.example.koalabee.esstoreapp.R;
import com.table.Saler;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SalerInfoActivity extends AppCompatActivity {

    private Button salerInfoOk;
    private CircleImageView salerinfoPic;
    private EditText salerInfoName, salerInfoPassowrd, salerInfoPhoneNum, salerInfoAddresss, salerInfoUqPwd;
    private RadioButton salerInfoMan, salerInfoWoman;

    private final int requestCode = 100;
    private final int CHOOSE_PHOTO = 101;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private List<String> permissionsList = new ArrayList<>();
    private Boolean hasPermissionDismiss = false;
    private String path = null;
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


    private void initPermissons() {
        permissionsList.clear();
        for (int i = 0;i<permissions.length;i++){
            if (ContextCompat.checkSelfPermission(SalerInfoActivity.this,permissions[i])
                    != PackageManager.PERMISSION_GRANTED){
                permissionsList.add(permissions[i]);
            }
        }
        if (permissionsList.size()>0){
            ActivityCompat.requestPermissions(SalerInfoActivity.this,permissions,requestCode);
        }else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (this.requestCode == requestCode){
            for (int i =0;i<grantResults.length;i++){
                if (grantResults[i] == -1){
                    hasPermissionDismiss = true;
                }
            }
            if (hasPermissionDismiss){
                finish();
            }else {
                openAlbum();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CHOOSE_PHOTO == requestCode){
            if (resultCode == RESULT_OK){
                handleImageOnKitKat(data);
            }
        }
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);

        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {

        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String path){
        Glide.with(SalerInfoActivity.this).load(path).into(salerinfoPic);
    }

    private void initEvents() {
        final Long saleId = getIntent().getLongExtra("salerid",-1);
        SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
        Saler saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(saleId)).unique();
        if (saler.getPicPath() == null)
            salerinfoPic.setImageResource(R.mipmap.ic_launcher);
        else{
            Bitmap bitmap = BitmapFactory.decodeFile(saler.getPicPath());
            salerinfoPic.setImageBitmap(bitmap);
        }
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

        salerinfoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPermissons();
            }
        });
        salerInfoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String salername = salerInfoName.getText().toString();
                String salerpwd = salerInfoPassowrd.getText().toString();
                String saleruqpwd = salerInfoUqPwd.getText().toString();
                String salerphone = salerInfoPhoneNum.getText().toString();
                String saleraddress = salerInfoAddresss.getText().toString();

                SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
                Saler saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(saleId)).unique();

                if (salername == null || salerpwd == null || saleruqpwd == null)
                    Toast.makeText(SalerInfoActivity.this,"用户名、密码或确认密码不能为空！",Toast.LENGTH_SHORT).show();
                else if (!(salerpwd.equals(saleruqpwd)))
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
                     saler.setPicPath(path);
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
