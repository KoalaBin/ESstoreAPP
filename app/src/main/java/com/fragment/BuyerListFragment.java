package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.BuyerListAdapter;
import com.anye.greendao.gen.BuyerDao;
import com.anye.greendao.gen.DaoSession;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.MyApplication;
import com.table.Buyer;

import java.util.ArrayList;
import java.util.List;


public class BuyerListFragment extends Fragment {
    private View view;
    private ListView lvBuyer;
    private List<Buyer> buyerList = new ArrayList<>();
    private BuyerListAdapter buyerListAdapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public BuyerListFragment(){

    }

    public static BuyerListFragment newInstance(String param1, String param2) {
        BuyerListFragment fragment = new BuyerListFragment();
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
        view = inflater.inflate(R.layout.fragment_user_list,container,false);
        lvBuyer = view.findViewById(R.id.lv_user);
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        BuyerDao buyerDao = daoSession.getBuyerDao();
        buyerList.addAll(buyerDao.queryBuilder().list());
        buyerListAdapter = new BuyerListAdapter(getContext(),buyerList);
        buyerListAdapter.setOnDeleteClickListener(new BuyerListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                DaoSession daoSession=MyApplication.getInstances().getDaoSession();
                BuyerDao buyerDao=daoSession.getBuyerDao();
                buyerDao.delete(buyerList.get(position));
                buyerList.clear();
                buyerList.addAll(buyerDao.queryBuilder().list());
                buyerListAdapter.notifyDataSetChanged();
            }
        });

        lvBuyer.setAdapter(buyerListAdapter);
        lvBuyer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"on item click",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void updateBuyerList(){
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        BuyerDao buyerDao = daoSession.getBuyerDao();
        buyerList.clear();
        buyerList.addAll(buyerDao.queryBuilder().list());
        buyerListAdapter.notifyDataSetChanged();
    }



}
