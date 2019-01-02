package com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koalabee.esstoreapp.R;
import com.table.Order;

import java.util.List;

/**
 * Created by Koala Bee on 2019/1/1.
 */

public class BuyerOrderListAdapter extends ArrayAdapter<Order> {
    public BuyerOrderListAdapter(@NonNull Context context, int resource, List<Order> orders) {
        super(context, resource, orders);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buyer_order_item, parent, false);
        }
        TextView tvProductName = convertView.findViewById(R.id.tv_product_name);
        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        TextView tvQuantity = convertView.findViewById(R.id.tv_quantity);
        TextView tvTotalPrice = convertView.findViewById(R.id.tv_total_price);
        TextView tvSalerName = convertView.findViewById(R.id.tv_saler_name);
        TextView tvDate = convertView.findViewById(R.id.tv_date);
        ImageView ivPic = convertView.findViewById(R.id.iv_pic);

        tvProductName.setText(getItem(position).getProductName());
        tvPrice.setText(String.valueOf("单价：" + getItem(position).getPrice()) + "元");
        tvQuantity.setText(String.valueOf(getItem(position).getQuantity()) + "件");
        tvTotalPrice.setText("总价：" + String.valueOf(getItem(position).getTotalPrice()) + "元");
        tvSalerName.setText(getItem(position).getSalerName());
        tvDate.setText(getItem(position).getCreatedDate());

        String picPath = getItem(position).getPicPath();
        if (picPath != null){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picPath, options);

            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            int ivPicWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getContext().getResources().getDisplayMetrics());
            int ivPicHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72, getContext().getResources().getDisplayMetrics());

            int ratio = Math.max(outWidth / ivPicWidth, outHeight / ivPicHeight);

            options.inJustDecodeBounds = false;
            options.inSampleSize = ratio;
            Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);

            ivPic.setImageBitmap(bitmap);
        }

        tvProductName.setText(getItem(position).getProductName());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
