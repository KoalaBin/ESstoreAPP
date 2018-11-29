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
import com.koalabee.esstore.SalerClass;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Koala Bee on 2018/11/23.
 */

public class SalerAdapter extends ArrayAdapter<SalerClass> {
    private View view;
   // private int resourceId;
    private TextView salerclasses;
    private TextView salercount;
    public SalerAdapter(@NonNull Context context, int resource, List<SalerClass> salerClasses) {
        super(context, resource,salerClasses);
       // resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       SalerClass salerClass = getItem(position);
       if (convertView == null)
       view = LayoutInflater.from(getContext()).inflate(R.layout.saler_item,parent,false);
       else{
           view = convertView;
       }
       salerclasses = view.findViewById(R.id.salerclasses);
       salercount = view.findViewById(R.id.salercount);
       salerclasses.setText(salerClass.getSale_class());
       salercount.setText(String.valueOf(salerClass.getSale_count()));
       return view;
    }
}
