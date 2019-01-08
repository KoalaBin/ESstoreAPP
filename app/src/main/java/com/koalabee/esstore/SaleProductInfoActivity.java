package com.koalabee.esstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Constants;
import com.MyApplication;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Product;

public class SaleProductInfoActivity extends AppCompatActivity {
    private ImageView imgPiSaler;
    private TextView piSalerName;
    private TextView piSalerPrice;
    private TextView piSalerQuantity;
    private TextView piSalerDescription;
    private Button piSalerOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_product_info);

        initViews();
        initInfo();
    }

    private void initInfo() {
        Long productid = getIntent().getLongExtra("product_id",-1);
        final ProductDao productDao = MyApplication.getInstances().getDaoSession().getProductDao();
        final Product product = productDao.queryBuilder().where(ProductDao.Properties.Id.eq(productid)).unique();
        Bitmap bitmap = BitmapFactory.decodeFile(product.getPicpath());
        imgPiSaler.setImageBitmap(bitmap);
        piSalerName.setText(product.getName());
        piSalerPrice.setText(product.getPrice().toString());
        piSalerQuantity.setText(product.getQuantity().toString());
        piSalerDescription.setText(product.getDescription());

        piSalerOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getCategory() == Constants.TYPE_CLOTHES){
                    productDao.delete(product);
                    Intent intent = new Intent(Constants.PRODUCT_EVENT);
                    intent.putExtra("out_product",Constants.OUT_CLOTHES);
                    SaleProductInfoActivity.this.sendBroadcast(intent);
                    Toast.makeText(SaleProductInfoActivity.this,"商品下架成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else if (product.getCategory() == Constants.TYPE_FRUIIT){
                    productDao.delete(product);
                    Intent intent = new Intent(Constants.PRODUCT_EVENT);
                    intent.putExtra("out_product",Constants.OUT_FRUITS);
                    SaleProductInfoActivity.this.sendBroadcast(intent);
                    Toast.makeText(SaleProductInfoActivity.this,"商品下架成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else if (product.getCategory() == Constants.TYPE_DRINK){
                    productDao.delete(product);
                    Intent intent = new Intent(Constants.PRODUCT_EVENT);
                    intent.putExtra("out_product",Constants.OUT_DRINK);
                    SaleProductInfoActivity.this.sendBroadcast(intent);
                    Toast.makeText(SaleProductInfoActivity.this,"商品下架成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initViews() {
        imgPiSaler = findViewById(R.id.img_pisaler);
        piSalerName = findViewById(R.id.tv_pisalername);
        piSalerPrice = findViewById(R.id.tv2_pisalerprice);
        piSalerQuantity = findViewById(R.id.tv_pisalerquantity);
        piSalerDescription = findViewById(R.id.tv_pisalerdescription);
        piSalerOut = findViewById(R.id.btn_pisalerout);

    }
}
