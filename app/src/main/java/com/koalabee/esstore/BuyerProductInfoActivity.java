package com.koalabee.esstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Constants;
import com.MyApplication;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.OrderDao;
import com.anye.greendao.gen.ProductDao;
import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;
import com.table.Order;
import com.table.Product;
import com.table.Saler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class BuyerProductInfoActivity extends AppCompatActivity {
    public static final String UPDATE_ORDER_LIST = "com.koalabee.esstore.activity.BuyerProductInfoActivity.update_order_list";
    private ImageView imgPiBuyer;
    private TextView piBuyerName;
    private TextView piBuyerPrice;
    private TextView piBuyerCount;
    private TextView piBuyerQuantity;
    private TextView piBuyerDescription;
    private Button piBuyerOk;
    private Button piBuyerShopCar;
    private ImageButton jia;
    private ImageButton jian;
    private Saler saler;
    private Product product;
    private Long salerId,buyerId,productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product_info);
        productId = getIntent().getLongExtra("product_id", -1);
        buyerId = getIntent().getLongExtra("buyerid",-1);
        ProductDao productDao = MyApplication.getInstances().getDaoSession().getProductDao();
        product = productDao.queryBuilder().where(ProductDao.Properties.Id.eq(productId)).unique();
        initViews();
        initEvents();
    }

    private void initEvents() {

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(piBuyerCount.getText().toString());
                piBuyerCount.setText(String.valueOf(count++));

            }
        });

        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(piBuyerCount.getText().toString());
                if (count <= 1)
                    jian.setClickable(false);
                piBuyerCount.setText(String.valueOf(count--));


            }
        });

        piBuyerOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = product.getName();
                Integer quantity = Integer.valueOf(piBuyerCount.getText().toString());
                Float price = Float.valueOf(product.getPrice());
                Float totalPrice = quantity * price;
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmsssss");
                String date = format.format(new Date());

                Integer leftQuantity = product.getQuantity() - quantity;
                if (leftQuantity < 0) {
                    Toast.makeText(BuyerProductInfoActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Order order = new Order();
                order.setProductName(productName);
                order.setPrice(price);
                order.setQuantity(quantity);
                order.setTotalPrice(totalPrice);
                order.setCreatedDate(date);
                order.setBuyerId(buyerId);
                order.setSalerId(salerId);
                order.setSalerName(saler.getName());
                order.setPicPath(product.getPicpath());
                order.setStatus(Constants.ORDER_STATUS_PAYED);
                order.setChecked(true);

                product.setQuantity(leftQuantity);

                final DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                daoSession.callInTxNoException(new Callable<Object>() {
                    @Override

                    public Object call() throws Exception {
                        OrderDao orderDao = daoSession.getOrderDao();
                        orderDao.insert(order);

                        ProductDao productDao = daoSession.getProductDao();
                        productDao.update(product);
                        return null;
                    }
                });

                Toast.makeText(BuyerProductInfoActivity.this, "购买成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UPDATE_ORDER_LIST);
                sendBroadcast(intent);
                finish();

            }
        });

        piBuyerShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = product.getName();
                Integer quantity = Integer.valueOf(piBuyerCount.getText().toString());
                Float price = Float.valueOf(product.getPrice());
                Float totalPrice = quantity * price;
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmsssss");
                String date = format.format(new Date());

                Integer leftQuantity = product.getQuantity() - quantity;
                if (leftQuantity < 0) {
                    Toast.makeText(BuyerProductInfoActivity.this, "库存不足", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Order order = new Order();
                order.setProductName(productName);
                order.setPrice(price);
                order.setQuantity(quantity);
                order.setTotalPrice(totalPrice);
                order.setBuyerId(buyerId);
                order.setSalerId(salerId);
                order.setCreatedDate(date);
                order.setSalerName(saler.getName());
                order.setPicPath(product.getPicpath());
                order.setStatus(Constants.ORDER_STATUS_SHOPPING_CAR);
                order.setChecked(true);

                product.setQuantity(leftQuantity);

                final DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                daoSession.callInTxNoException(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        OrderDao orderDao = daoSession.getOrderDao();
                        orderDao.insert(order);

                        ProductDao productDao = daoSession.getProductDao();
                        productDao.update(product);
                        return null;
                    }
                });

                Toast.makeText(BuyerProductInfoActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    private void initViews() {
        imgPiBuyer = findViewById(R.id.img_pibuyer);
        piBuyerName = findViewById(R.id.tv_pibuyername);
        piBuyerPrice = findViewById(R.id.tv2_pibuyerprice);
        piBuyerQuantity = findViewById(R.id.tv_pibuyerquantity);
        piBuyerDescription = findViewById(R.id.tv_pibuyerdescription);
        piBuyerOk = findViewById(R.id.btn_pibuyerok);
        piBuyerShopCar = findViewById(R.id.btn_pibuyershopcar);
        piBuyerCount = findViewById(R.id.et_pibuyercount);
        jia = findViewById(R.id.img_jia);
        jian = findViewById(R.id.img_jian);

        if (product != null) {
            salerId = product.getSaleId();
            SalerDao salerDao = MyApplication.getInstances().getDaoSession().getSalerDao();
            saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(salerId)).unique();
            piBuyerName.setText(product.getName());
            piBuyerPrice.setText(product.getPrice().toString());
            piBuyerQuantity.setText(product.getQuantity().toString());
            piBuyerDescription.setText(product.getDescription());
            final String picPath = product.getPicpath();
            if (picPath != null) {
                imgPiBuyer.post(new Runnable() {
                    @Override
                    public void run() {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(picPath, options);

                        int outWidth = options.outWidth;
                        int outHeight = options.outHeight;
                        int ivPicWidth = imgPiBuyer.getWidth();
                        int ivPicHeight = imgPiBuyer.getHeight();
                        int ratio = Math.max(outWidth / ivPicWidth, outHeight / ivPicHeight);

                        options.inJustDecodeBounds = false;
                        options.inSampleSize = ratio;
                        Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);

                        imgPiBuyer.setImageBitmap(bitmap);
                    }
                });

            }

        }
    }
}
