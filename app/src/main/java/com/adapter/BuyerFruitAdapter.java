package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Constants;
import com.MyApplication;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.ProductDao;
import com.bumptech.glide.Glide;
import com.example.koalabee.esstoreapp.R;
import com.koalabee.esstore.BuyerProductInfoActivity;
import com.table.Product;

import java.util.List;

/**
 * Created by Koala Bee on 2018/12/29.
 */

public class BuyerFruitAdapter extends RecyclerView.Adapter<BuyerFruitAdapter.ViewHolder> {
    private Context context;
    private List<Product> fruitlList;
    private Long productId;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView productImg;
        TextView productTxt;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            productImg = view.findViewById(R.id.img_product);
            productTxt = view.findViewById(R.id.txt_product);

        }
    }

    public BuyerFruitAdapter(List<Product> fruitList){
        this.fruitlList = fruitlList;
    }
    @NonNull
    @Override
    public BuyerFruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        final BuyerFruitAdapter.ViewHolder holder = new BuyerFruitAdapter.ViewHolder(view);
        holder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyerProductInfoActivity.class);
                intent.putExtra("product_id",productId);
                context.startActivity(intent);

            }
        });

        return new BuyerFruitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        ProductDao productDao = daoSession.getProductDao();
        fruitlList = productDao.queryBuilder().where(ProductDao.Properties.Category.eq(Constants.TYPE_FRUIIT)).list();
        Product product = fruitlList.get(position);
        holder.productTxt.setText(product.getName());
        Glide.with(context).load(product.getPicpath()).into(holder.productImg);
        productId = product.getId();
    }

    @Override
    public int getItemCount() {
        return fruitlList.size();
    }
}