package com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.SalerDao;
import com.anye.greendao.gen.DaoSession;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.AdminActivity;
import com.MyApplication;
import com.table.Buyer;
import com.table.Saler;


public class AddUserFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etUsr, etPwd;
    private RadioButton rbBuyer, rbSaler;
    private Button btnAddUser;
    private View view;



    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
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
        view=inflater.inflate(R.layout.fragment_add_user,container,false);
        initViews();
        initEvents();
        return view;
    }

    private void initEvents() {
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoSession daoSession= MyApplication.getInstances().getDaoSession();
                String name=etUsr.getText().toString();
                String pwd=etPwd.getText().toString();
                if (rbBuyer.isChecked()){
                    BuyerDao buyerDao=daoSession.getBuyerDao();
                    Buyer buyer=new Buyer();
                    buyer.setName(name);
                    buyer.setPassword(pwd);
                    buyerDao.insert(buyer);
                    Intent intent=new Intent();
                    intent.setAction(AdminActivity.ADD_USER_SUCCESS);
                    intent.putExtra("user_type",AdminActivity.ADD_BUYER);
                    getActivity().sendBroadcast(intent);
                    Toast.makeText(getContext(),"用户添加成功",Toast.LENGTH_SHORT).show();
                 }else if (rbSaler.isChecked()){
                    SalerDao salerDao=daoSession.getSalerDao();
                    Saler saler=new Saler();
                    saler.setName(name);
                    saler.setPassword(pwd);
                    salerDao.insert(saler);
                    Intent intent=new Intent();
                    intent.setAction(AdminActivity.ADD_USER_SUCCESS);
                    intent.putExtra("user_type",AdminActivity.ADD_SALER);
                    getActivity().sendBroadcast(intent);
                    Toast.makeText(getContext(),"用户添加成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        etUsr=view.findViewById(R.id.et_user);
        etPwd=view.findViewById(R.id.et_pwd);
        rbBuyer=view.findViewById(R.id.rb_buyer);
        rbSaler=view.findViewById(R.id.rb_saler);
        btnAddUser=view.findViewById(R.id.btn_add_user);
    }


}
