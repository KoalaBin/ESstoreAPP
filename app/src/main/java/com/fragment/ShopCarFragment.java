package com.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.Constants;
import com.MyApplication;
import com.adapter.BuyerShoppingCarAdapter;
import com.anye.greendao.gen.OrderDao;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.BuyerProductInfoActivity;
import com.table.Order;

import java.util.ArrayList;
import java.util.List;


public class ShopCarFragment extends Fragment {
    private Long buyerId;
    private ListView lvShoppingCar;
    private CheckBox cbAll;
    private TextView tvTotalPrice;
    private Button btnPay;
    private List<Order> orders = new ArrayList<>();
    private BuyerShoppingCarAdapter adapter;
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public static ShopCarFragment newInstance(String param1, String param2) {
        ShopCarFragment fragment = new ShopCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_shop_car,container,false);
        buyerId = getActivity().getIntent().getLongExtra("buyerid",-1);
        initViews();
        initEvents();
        return view;
    }

    private void initViews() {
        lvShoppingCar = view.findViewById(R.id.lv_shopping_car);
        cbAll = view.findViewById(R.id.cb_all);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        btnPay = view.findViewById(R.id.btn_pay);

        OrderDao orderDao = MyApplication.getInstances().getDaoSession().getOrderDao();
        orders.addAll(orderDao.queryBuilder().where(OrderDao.Properties.BuyerId.eq(buyerId), OrderDao.Properties.Status.eq(Constants.ORDER_STATUS_SHOPPING_CAR)).list());
        adapter = new BuyerShoppingCarAdapter(getContext(), R.layout.shop_car_item, orders);
        lvShoppingCar.setAdapter(adapter);

        boolean flag = true;
        for (Order order : orders){
            if (!order.getChecked()){
                flag = false;
                break;
            }
        }
        cbAll.setChecked(flag);

        Float totalPrice = 0f;
        for (Order order : orders){
            if (order.getChecked()){
                totalPrice += order.getTotalPrice();
            }
        }
        tvTotalPrice.setText("合计：" + String.valueOf(totalPrice) + "元");
    }

    private void initEvents() {
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Order order : orders){
                    order.setChecked(cbAll.isChecked());
                }
                adapter.notifyDataSetChanged();

                Float totalPrice = 0f;
                for (Order order : orders){
                    if (order.getChecked()){
                        totalPrice += order.getTotalPrice();
                    }
                }
                tvTotalPrice.setText("合计：" + String.valueOf(totalPrice) + "元");
            }
        });

        adapter.setOnAdapterListener(new BuyerShoppingCarAdapter.OnAdapterListener() {
            @Override
            public void onCheckBox() {
                boolean flag = true;
                for (Order order : orders){
                    if (!order.getChecked()){
                        flag = false;
                        break;
                    }
                }
                cbAll.setChecked(flag);
            }

            @Override
            public void onTotalPrice() {
                Float totalPrice = 0f;
                for (Order order : orders){
                    if (order.getChecked()){
                        totalPrice += order.getTotalPrice();
                    }
                }
                tvTotalPrice.setText("合计：" + String.valueOf(totalPrice) + "元");
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Order> orderList = new ArrayList<>();
                for (Order order : orders){
                    if (order.getChecked()){
                        order.setStatus(Constants.ORDER_STATUS_PAYED);
                        orderList.add(order);
                    }
                }

                OrderDao orderDao = MyApplication.getInstances().getDaoSession().getOrderDao();
                orderDao.updateInTx(orderList);

                Intent intent = new Intent(BuyerProductInfoActivity.UPDATE_ORDER_LIST);
                getActivity().sendBroadcast(intent);
            }
        });
    }

}
