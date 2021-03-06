package com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.koalabee.esstoreapp.R;
import com.UserClass;

import java.util.List;

/**
 * Created by Koala Bee on 2018/11/23.
 */

public class SalerAdapter extends ArrayAdapter<UserClass> {
    private View view;
    private TextView salerclasses;
    public SalerAdapter(@NonNull Context context, int resource, List<UserClass> userClasses) {
        super(context, resource, userClasses);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       UserClass salerClass = getItem(position);
       if (convertView == null)
       view = LayoutInflater.from(getContext()).inflate(R.layout.saler_item,parent,false);
       else{
           view = convertView;
       }
       salerclasses = view.findViewById(R.id.salerclasses);
       salerclasses.setText(salerClass.getUser_class());
       return view;
    }

    @Nullable
    @Override
    public UserClass getItem(int position) {
        return super.getItem(position);
    }
}
