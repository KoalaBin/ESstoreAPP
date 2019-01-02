package com.koalabee.esstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MyApplication;
import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.OrderDao;
import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;
import com.table.Order;
import com.table.Saler;

public class BuyerOrderDetailActivity extends AppCompatActivity {
    private ImageView ivPicProduct, ivPicSaler, ivPicBuyer;
    private TextView tvProductName, tvPrice, tvQuantity, tvTotalPrice, tvSalerName, tvSalerGender, tvSalerTel, tvSalerAddress, tvBuyerName;
    private Button btnDeleteOrder;
    private Long orderId, salerId, buyerId;
    private Order order;
    private Saler saler;
    private Buyer buyer;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order_detail);

        orderId = getIntent().getLongExtra("order_id", -1);
        daoSession = MyApplication.getInstances().getDaoSession();
        OrderDao orderDao = daoSession.getOrderDao();
        order = orderDao.queryBuilder().where(OrderDao.Properties.Id.eq(orderId)).unique();

        if (order != null) {
            salerId = order.getSalerId();
            buyerId = order.getBuyerId();

        }

        initViews();
        initEvents();
    }

    private void initEvents() {
        btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BuyerOrderDetailActivity.this)
                        .setTitle("取消订单")
                        .setMessage("是否确认要取消该订单")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                OrderDao orderDao = daoSession.getOrderDao();
                                orderDao.delete(order);
                                Toast.makeText(BuyerOrderDetailActivity.this, "订单取消成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BuyerProductInfoActivity.UPDATE_ORDER_LIST);
                                sendBroadcast(intent);
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void initViews() {
        ivPicProduct = findViewById(R.id.iv_pic_product);
        ivPicSaler = findViewById(R.id.iv_pic_saler);
        ivPicBuyer = findViewById(R.id.iv_pic_buyer);

        tvProductName = findViewById(R.id.tv_product_name);
        tvPrice = findViewById(R.id.tv_price);
        tvQuantity = findViewById(R.id.tv_quantity);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvSalerName = findViewById(R.id.tv_saler_name);
        tvSalerGender = findViewById(R.id.tv_gender);
        tvSalerTel = findViewById(R.id.tv_saler_tel);
        tvSalerAddress = findViewById(R.id.tv_saler_address);
        tvBuyerName = findViewById(R.id.tv_buyer_name);
        btnDeleteOrder = findViewById(R.id.btn_delete_order);

        BuyerDao buyerDao = daoSession.getBuyerDao();
        buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();

        SalerDao salerDao = daoSession.getSalerDao();
        saler = salerDao.queryBuilder().where(SalerDao.Properties.Id.eq(salerId)).unique();

        tvProductName.setText("商品名称：" + order.getProductName());
        ;
        tvPrice.setText("单价：" + String.valueOf(order.getPrice()) + "元");
        tvQuantity.setText(String.valueOf(order.getQuantity()) + "件");
        tvTotalPrice.setText("总价：" + String.valueOf(order.getTotalPrice()) + "元");

        tvSalerName.setText("卖家名称：" + saler.getName());
        tvSalerGender.setText("性别：" + saler.getGender());
        tvSalerTel.setText("电话号码：" + saler.getPhoneNum());
        tvSalerAddress.setText("地址：" + saler.getAddress());

        tvBuyerName.setText("买家名称：" + buyer.getName());

        String picPathProduct = order.getPicPath();
        String picPathSaler = saler.getPicPath();

        if (picPathProduct != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picPathProduct, options);

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            int ivPicWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getResources().getDisplayMetrics());
            int ivPicHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getResources().getDisplayMetrics());

            int ratio = Math.max(outWidth / ivPicWidth, outHeight / ivPicHeight);

            options.inJustDecodeBounds = false;
            options.inSampleSize = ratio;
            Bitmap bitmap = BitmapFactory.decodeFile(picPathProduct, options);

            ivPicProduct.setImageBitmap(bitmap);
        }

        if (picPathSaler != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picPathSaler, options);

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            int ivPicWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getResources().getDisplayMetrics());
            int ivPicHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getResources().getDisplayMetrics());

            int ratio = Math.max(outWidth / ivPicWidth, outHeight / ivPicHeight);

            options.inJustDecodeBounds = false;
            options.inSampleSize = ratio;
            Bitmap bitmap = BitmapFactory.decodeFile(picPathSaler, options);

            ivPicSaler.setImageBitmap(bitmap);
        }

    }
}
