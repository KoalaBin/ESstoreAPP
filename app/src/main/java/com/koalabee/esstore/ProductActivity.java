package com.koalabee.esstore;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.FontRequest;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Product;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.OAEPParameterSpec;

public class ProductActivity extends AppCompatActivity {
    private ImageButton ibtnProduct;
    private EditText etProductName;
    private EditText etProductPrice;
    private EditText etProductDescription;
    private RadioButton rbFruit;
    private RadioButton rbClothes;
    private RadioButton rbDrink;
    private Button btnOk;

    private final int requestCode = 100;
    private final int CHOOSE_PHOTO = 101;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private List<String> permissionsList = new ArrayList<>();
    private Boolean hasPermissionDismiss = false;
    public static final String ADD_PRODUCT_SUCCESS = "com.koalabee.esstore.ProductActivity.add_product_success";
    public static final int ADD_PRODUCT = 5;
    String path = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();
        initEvents();

    }

    private void initPermissons() {
        permissionsList.clear();
        for (int i = 0;i<permissions.length;i++){
            if (ContextCompat.checkSelfPermission(ProductActivity.this,permissions[i])
                    != PackageManager.PERMISSION_GRANTED){
                permissionsList.add(permissions[i]);
            }
        }
        if (permissionsList.size()>0){
            ActivityCompat.requestPermissions(ProductActivity.this,permissions,requestCode);
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

    private void displayImage(String imagePath) {
        if (imagePath != null){
         Bitmap bitmap = compressImageFromFile(imagePath);
         ibtnProduct.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"找不到文件",Toast.LENGTH_SHORT).show();
        }

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
    private Bitmap compressImageFromFile(String srcPath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap;
        options.inJustDecodeBounds = true;

        int w = options.outWidth;
        int h = options.outHeight;
        int ww = ibtnProduct.getWidth();
        int hh = ibtnProduct.getHeight();
        int be = 1;
        if (w > h && w > ww){
            be = options.outWidth/ww;
        }else if (w < h && h > hh){
            be = options.outHeight/hh;
        }
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(srcPath);

        return bitmap;

    }
    private void initEvents() {
        ibtnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPermissons();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pdName = etProductName.getText().toString();
                Float pdPrice = Float.parseFloat(etProductPrice.getText().toString());
                String pdDescription = etProductDescription.getText().toString();
                int pdQuantity = Constants.TYPE_CLOTHES;
                if (rbClothes.isChecked())
                    pdQuantity = Constants.TYPE_CLOTHES;
                else if (rbFruit.isChecked())
                    pdQuantity = Constants.TYPE_FRUIIT;
                else if (rbDrink.isChecked())
                    pdQuantity = Constants.TYPE_DRINK;

                DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                ProductDao productDao = daoSession.getProductDao();
                Product product = new Product();
                product.setPicpath(path);
                product.setName(pdName);
                product.setPrice(pdPrice);
                product.setDescription(pdDescription);
                product.setQuantity(pdQuantity);
                productDao.insert(product);

                Intent intent = new Intent();
                intent.setAction(ProductActivity.ADD_PRODUCT_SUCCESS);
                intent.putExtra("user_type",ProductActivity.ADD_PRODUCT);
                intent.setClass(ProductActivity.this,SalerActivity.class);
                startActivity(intent);
                ProductActivity.this.sendBroadcast(intent);

            }
        });
    }

    private void initViews() {
        ibtnProduct = findViewById(R.id.ibtn_product);
        etProductName = findViewById(R.id.et_productname);
        etProductPrice = findViewById(R.id.et_productprice);
        etProductDescription = findViewById(R.id.et_productdescription);
        rbFruit = findViewById(R.id.rb_fruit);
        rbClothes = findViewById(R.id.rb_clothes);
        rbDrink = findViewById(R.id.rb_drink);
        btnOk = findViewById(R.id.btn_ok);
    }
}
