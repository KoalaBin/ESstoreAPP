package com.koalabee.esstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Constants;
import com.MyApplication;
import com.adapter.BuyerOrderListAdapter;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.OrderDao;
import com.example.koalabee.esstoreapp.R;
import com.table.Order;

import java.util.ArrayList;
import java.util.List;

public class BuyerOrderActivity extends AppCompatActivity {
    private ListView lvOrder;
    private BuyerOrderListAdapter adapter;
    private List<Order> orders = new ArrayList<>();
    private UpdateOrderListReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_order);
        initViews();
        initEvents();

    }

    private void initEvents() {
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuyerOrderActivity.this, BuyerOrderDetailActivity.class);
                intent.putExtra("order_id", id);
                startActivity(intent);
            }


        });
    }

    private void initViews() {
        Long id = getIntent().getLongExtra("buyerid",-1);
        lvOrder = findViewById(R.id.lv_order);
        OrderDao orderDao = MyApplication.getInstances().getDaoSession().getOrderDao();
        orders.addAll(orderDao.queryBuilder().where(OrderDao.Properties.BuyerId.eq(id), OrderDao.Properties.Status.eq(Constants.ORDER_STATUS_PAYED)).list());
        adapter = new BuyerOrderListAdapter(BuyerOrderActivity.this, R.layout.buyer_order_item, orders);
        lvOrder.setAdapter(adapter);
    }

    private class UpdateOrderListReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Long id = getIntent().getLongExtra("buyerid",-1);
            DaoSession daoSession = MyApplication.getInstances().getDaoSession();
            OrderDao orderDao = daoSession.getOrderDao();
            orders.clear();
            orders.addAll(orderDao.queryBuilder().where(OrderDao.Properties.BuyerId.eq(id), OrderDao.Properties.Status.eq(Constants.ORDER_STATUS_PAYED)).list());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
