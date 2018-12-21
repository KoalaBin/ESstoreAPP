package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapter.ClothesAdapter;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.Constants;
import com.MyApplication;
import com.table.Product;

import java.util.ArrayList;
import java.util.List;


public class ClothesFragment extends Fragment {

    private static final String SALE_ID = "saleid";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private RecyclerView rvOnsalePrduct;
    private List<Product> clothesList = new ArrayList<>();
    private ClothesAdapter clothesAdapter;
    private String mParam1;
    private String mParam2;



    public static ClothesFragment newInstance(String param1, String param2) {
       ClothesFragment fragment = new ClothesFragment();
        Bundle args = new Bundle();
        args.putString(SALE_ID, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(SALE_ID);
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
        clothesList = productDao.queryBuilder().where(ProductDao.Properties.Category.eq(Constants.TYPE_CLOTHES)).list();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rvOnsalePrduct.setLayoutManager(layoutManager);
        clothesAdapter = new ClothesAdapter(clothesList);
        rvOnsalePrduct.setAdapter(clothesAdapter);
        return view;
    }

    public void updateClothesList(){
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        ProductDao productDao = daoSession.getProductDao();
        clothesList.clear();
        clothesList = productDao.queryBuilder().where(ProductDao.Properties.Category.eq(Constants.TYPE_CLOTHES)).list();
        clothesAdapter.notifyDataSetChanged();
    }
    }
