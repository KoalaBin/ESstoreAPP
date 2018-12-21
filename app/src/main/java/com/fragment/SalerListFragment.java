package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.SalerListAdapter;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.SalerDao;
import com.example.koalabee.esstoreapp.R;
import com.MyApplication;
import com.table.Saler;

import java.util.ArrayList;
import java.util.List;

public class SalerListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private ListView lvSaler;
    private List<Saler> salerList = new ArrayList<>();
    private SalerListAdapter salerListAdapter;

    private String mParam1;
    private String mParam2;




    public static SalerListFragment newInstance(String param1, String param2) {
        SalerListFragment fragment = new SalerListFragment();
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
        view=inflater.inflate(R.layout.fragment_user_list,container,false);
        lvSaler = view.findViewById(R.id.lv_user);
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        SalerDao salerDao = daoSession.getSalerDao();
        salerList.addAll(salerDao.queryBuilder().list());
        salerListAdapter = new SalerListAdapter(getContext(),salerList);
        salerListAdapter.setOnDeleteClickListener(new SalerListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                DaoSession daoSession=MyApplication.getInstances().getDaoSession();
                SalerDao salerDao=daoSession.getSalerDao();
                salerDao.delete(salerList.get(position));
                salerList.clear();
                salerList.addAll(salerDao.queryBuilder().list());
                salerListAdapter.notifyDataSetChanged();
            }
        });

        lvSaler.setAdapter(salerListAdapter);
        lvSaler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"on item click",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void updateSalerList(){
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        SalerDao salerDao = daoSession.getSalerDao();
        salerList.clear();
        salerList.addAll(salerDao.queryBuilder().list());
        salerListAdapter.notifyDataSetChanged();
    }

}
