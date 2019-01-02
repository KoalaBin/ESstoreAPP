package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapter.SalerDrinkAdapter;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.Constants;
import com.MyApplication;
import com.table.Product;

import java.util.ArrayList;
import java.util.List;

public class SalerDrinkFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private RecyclerView rvOnsalePrduct;
    private List<Product> drinkList = new ArrayList<>();
    private SalerDrinkAdapter salerDrinkAdapter;
    private String mParam1;
    private String mParam2;



    public static SalerDrinkFragment newInstance(String param1, String param2) {
        SalerDrinkFragment fragment = new SalerDrinkFragment();
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
        view = inflater.inflate(R.layout.fragment_clothes,container,false);
        rvOnsalePrduct = view.findViewById(R.id.rv_onsaleproduct);
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        ProductDao productDao = daoSession.getProductDao();
        drinkList = productDao.queryBuilder().where(ProductDao.Properties.Category.eq(Constants.TYPE_DRINK)).list();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rvOnsalePrduct.setLayoutManager(layoutManager);
        salerDrinkAdapter = new SalerDrinkAdapter(drinkList);
        rvOnsalePrduct.setAdapter(salerDrinkAdapter);
        return view;
    }

    public void updateDrinkList(){
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        ProductDao productDao = daoSession.getProductDao();
        drinkList.clear();
        drinkList = productDao.queryBuilder().where(ProductDao.Properties.Category.eq(Constants.TYPE_DRINK)).list();
        salerDrinkAdapter.notifyDataSetChanged();
    }
}
