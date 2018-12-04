package com.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.ProductDao;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.MyApplication;
import com.table.Product;

import org.greenrobot.greendao.query.QueryBuilder;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Koala Bee on 2018/12/3.
 */

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    private Context context;
    private List<Product> clothesList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView productImg;
        TextView productTxt;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            productImg = view.findViewById(R.id.img_product);
            productTxt = view.findViewById(R.id.txt_product);

            DaoSession daoSession = MyApplication.getInstances().getDaoSession();
            ProductDao productDao = daoSession.getProductDao();
        }
    }
    public ClothesAdapter(List<Product> clothesList){
        this.clothesList = clothesList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
