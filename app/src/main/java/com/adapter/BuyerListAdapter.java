package com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.koalabee.esstoreapp.R;
import com.table.Buyer;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * Created by Koala Bee on 2018/11/15.
 */

public class BuyerListAdapter extends BaseAdapter {
    private Context context;
    private List<Buyer> buyers;
    private OnDeleteClickListener onDeleteClickListener;

    public BuyerListAdapter(@NonNull Context context,@NotNull List<Buyer> buyers) {
        this.context=context;
        this.buyers=buyers;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item,parent,false);
        }

        TextView tvUsr = convertView.findViewById(R.id.tv_usr);
        TextView tvPwd = convertView.findViewById(R.id.tv_pwd);
        TextView tvId = convertView.findViewById(R.id.tv_id);
        Button btnDelete = convertView.findViewById(R.id.btn_delete);
        tvUsr.setText(getItem(position).getName());
        tvPwd.setText(getItem(position).getPassword());
        tvId.setText(getItem(position).getId().toString());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener!=null){
                    onDeleteClickListener.onDeleteClick(position);
                }
            }
        });
        return convertView;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener){
        this.onDeleteClickListener=onDeleteClickListener;
    }


    @Override
    public int getCount() {
        return buyers.size();
    }

    @Nullable
    @Override
    public Buyer getItem(int position) {
        return buyers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnDeleteClickListener{
        void onDeleteClick(int position);
    }
}
