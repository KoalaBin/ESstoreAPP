package com.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.MyApplication;
import com.UserClass;
import com.adapter.BuyerAdapter;
import com.adapter.SalerAdapter;
import com.anye.greendao.gen.BuyerDao;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.BuyerPhoneNumActivity;
import com.table.Buyer;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonalBuyerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public List<UserClass> buyerClasses = new ArrayList<>();
    private BuyerAdapter buyerAdapter;
    private ListView listView;
    private CircleImageView buyerheadPic;
    private TextView buyerName;
    private View view;


    public static PersonalBuyerFragment newInstance(String param1, String param2) {
        PersonalBuyerFragment fragment = new PersonalBuyerFragment();
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

        view = inflater.inflate(R.layout.fragment_personal_buyer,container,false);

        initViews();
        initHeadPicName();
        initBuyerClass();
        initEvents();

        buyerAdapter = new BuyerAdapter(getContext(),R.layout.buyer_item,buyerClasses);
        listView.setAdapter(buyerAdapter);

        return view;
    }

    private void initEvents() {
       final Long buyerId = getActivity().getIntent().getLongExtra("buyerid",-1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                    case 1:
                    case 2:
                        Intent intent = new Intent(getActivity(), BuyerPhoneNumActivity.class);
                        intent.putExtra("buyerid",buyerId);
                        startActivity(intent);
                }
            }
        });
    }

    private void initBuyerClass() {

        Long buyerId = getActivity().getIntent().getLongExtra("buyerid",-1);
        BuyerDao buyerDao = MyApplication.getInstances().getDaoSession().getBuyerDao();
        Buyer buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();

        UserClass myOrder = new UserClass("我的订单",null);
        buyerClasses.add(myOrder);

        if (buyer.getGender() == null){
            UserClass myGender = new UserClass("性别","男");
            buyerClasses.add(myGender);
        }else{
            UserClass myGender = new UserClass("性别",buyer.getGender());
            buyerClasses.add(myGender);
        }

        if (buyer.getPhoneNum() == null){
            UserClass myPhoneNum = new UserClass("手机号码",null);
            buyerClasses.add(myPhoneNum);
        }else{
        UserClass myPhoneNum = new UserClass("手机号码",buyer.getPhoneNum());
        buyerClasses.add(myPhoneNum);
        }

        UserClass myAddress = new UserClass("我的收货地址",null);
        buyerClasses.add(myAddress);
    }

    private void initHeadPicName() {
        Long buyerId = getActivity().getIntent().getLongExtra("buyerid",-1);
        BuyerDao buyerDao = MyApplication.getInstances().getDaoSession().getBuyerDao();
        Buyer buyer = buyerDao.queryBuilder().where(BuyerDao.Properties.Id.eq(buyerId)).unique();
        if(buyer.getPicPath() == null){
            buyerName.setText(buyer.getName());
           buyerheadPic.setImageResource(R.mipmap.ic_launcher);
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(buyer.getPicPath());
            buyerName.setText(buyer.getName());
            buyerheadPic.setImageBitmap(bitmap);
        }
    }

    private void initViews() {
        listView = view.findViewById(R.id.lv_buyerclass);
        buyerheadPic = view.findViewById(R.id.buyerheadpic);
        buyerName = view.findViewById(R.id.buyername);
    }

}
