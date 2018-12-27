package com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.UserClass;
import com.example.koalabee.esstoreapp.R;

import java.util.List;

/**
 * Created by Koala Bee on 2018/12/24.
 */

public class BuyerAdapter extends ArrayAdapter<UserClass> {
    private View view;
    private TextView buyerclasses;
    private TextView buyerinfo;
    public BuyerAdapter(@NonNull Context context, int resource, List<UserClass> buyerClasses) {
        super(context, resource,buyerClasses);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserClass buyerClass = getItem(position);
        if (convertView == null)
            view = LayoutInflater.from(getContext()).inflate(R.layout.buyer_item,parent,false);
        else{
            view = convertView;
        }
        buyerclasses = view.findViewById(R.id.buyerclasses);
        buyerinfo = view.findViewById(R.id.buyerinfo);
        buyerclasses.setText(buyerClass.getUser_class());
        buyerinfo.setText(buyerClass.getUser_info());
        return view;
    }

    @Nullable
    @Override
    public UserClass getItem(int position) {
        return super.getItem(position);
    }
}
