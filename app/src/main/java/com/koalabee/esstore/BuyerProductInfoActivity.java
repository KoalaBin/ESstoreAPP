package com.koalabee.esstore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyApplication;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Product;

public class BuyerProductInfoActivity extends AppCompatActivity {
    private ImageView imgPiBuyer;
    private TextView piBuyerName;
    private TextView piBuyerPrice;
    private EditText piBuyerCount;
    private TextView piBuyerQuantity;
    private TextView piBuyerDescription;
    private Button piBuyerOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product_info);

        initViews();
        initEvents();
    }

    private void initEvents() {
        String name = getIntent().getStringExtra("product_name");
        final ProductDao productDao = MyApplication.getInstances().getDaoSession().getProductDao();
        final Product product = productDao.queryBuilder().where(ProductDao.Properties.Name.eq(name)).unique();
        Bitmap bitmap = BitmapFactory.decodeFile(product.getPicpath());
        imgPiBuyer.setImageBitmap(bitmap);
        piBuyerName.setText(product.getName());
        piBuyerPrice.setText(product.getPrice().toString());
        piBuyerQuantity.setText(product.getQuantity().toString());
        piBuyerDescription.setText(product.getDescription());

    }

    private void initViews() {
        imgPiBuyer = findViewById(R.id.img_pibuyer);
        piBuyerName = findViewById(R.id.tv_pibuyername);
        piBuyerPrice = findViewById(R.id.tv2_pibuyerprice);
        piBuyerQuantity = findViewById(R.id.tv_pibuyerquantity);
        piBuyerDescription = findViewById(R.id.tv_pibuyerdescription);
        piBuyerOk = findViewById(R.id.btn_pibuyerok);
        piBuyerCount = findViewById(R.id.et_picount);
    }

}

