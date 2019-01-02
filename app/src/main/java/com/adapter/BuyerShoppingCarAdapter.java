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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koalabee.esstoreapp.R;
import com.table.Order;

import java.util.List;

/**
 * Created by Koala Bee on 2019/1/2.
 */

public class BuyerShoppingCarAdapter extends ArrayAdapter<Order> {
    private OnAdapterListener onAdapterListener;
    public BuyerShoppingCarAdapter(@NonNull Context context, int resource, List<Order> orders) {
        super(context, resource, orders);
    }

    public void setOnAdapterListener(OnAdapterListener onAdapterListener) {
        this.onAdapterListener = onAdapterListener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int quantity = getItem(position).getQuantity();
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_car_item, parent, false);
        }
        TextView tvProductName = convertView.findViewById(R.id.tv_product_name);
        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        TextView tvQuantity = convertView.findViewById(R.id.tv_quantity);
        final CheckBox cb = convertView.findViewById(R.id.cb);
        ImageView ivPic = convertView.findViewById(R.id.iv_pic);
        final ImageView ivSub = convertView.findViewById(R.id.iv_sub);
        ImageView ivPlus = convertView.findViewById(R.id.iv_plus);
        TextView tvTotalPrice = convertView.findViewById(R.id.tv_total_price);

        tvProductName.setText(getItem(position).getProductName());
        tvPrice.setText("￥" + String.valueOf(getItem(position).getPrice()) + "元");
        tvQuantity.setText(String.valueOf(getItem(position).getQuantity()));
        tvTotalPrice.setText("小计：" + String.valueOf(getItem(position).getTotalPrice()) + "元");
        if (quantity <= 1){
            ivSub.setEnabled(false);
        }

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

        cb.setChecked(getItem(position).getChecked());
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).setChecked(cb.isChecked());
                if (onAdapterListener != null){
                    onAdapterListener.onCheckBox();
                    onAdapterListener.onTotalPrice();
                }
            }
        });

        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = getItem(position).getQuantity();
                quantity--;
                if (quantity <= 1){
                    ivSub.setEnabled(false);
                }
                getItem(position).setQuantity(quantity);
                getItem(position).setTotalPrice(quantity * getItem(position).getPrice());
                if (onAdapterListener != null){
                    onAdapterListener.onTotalPrice();
                }
                notifyDataSetChanged();
            }
        });

        ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = getItem(position).getQuantity();
                quantity++;
                if (quantity >= 2){
                    ivSub.setEnabled(true);
                }
                getItem(position).setQuantity(quantity);
                getItem(position).setTotalPrice(quantity * getItem(position).getPrice());
                if (onAdapterListener != null){
                    onAdapterListener.onTotalPrice();
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public interface OnAdapterListener{
        void onCheckBox();
        void onTotalPrice();
    }
}
