package com.bway.caoyuan.adapter;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.ordel.DetailList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class OrderitemAdapter extends RecyclerView.Adapter<OrderitemAdapter.ViewHolder> {

    Context mContext;

    List<DetailList> mResultBeans;


    public OrderitemAdapter(Context commodityFragment) {
        mContext = commodityFragment;
        mResultBeans = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View pzshview = View.inflate(mContext, R.layout.activity_orderfragmentitem_item, null);
        ViewHolder viewHolder = new ViewHolder(pzshview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String commodityPic = mResultBeans.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");
        Uri uri = Uri.parse(split[0]);
        viewHolder.cart_item_drawee.setImageURI(uri);
        viewHolder.cart_item_name.setText(mResultBeans.get(i).getCommodityName());
        viewHolder.cart_item_price.setText( mResultBeans.get(i).getCommodityPrice()+"");
        ;



    }


    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }

    public void addAll(List<DetailList> detailList) {
        mResultBeans.addAll(detailList);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView cart_item_drawee;
        public TextView cart_item_name;
        public TextView cart_item_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cart_item_drawee = (SimpleDraweeView) itemView.findViewById(R.id.cart_item_drawee);
            this.cart_item_name = (TextView) itemView.findViewById(R.id.cart_item_name);
            this.cart_item_price = (TextView) itemView.findViewById(R.id.cart_item_price);
        }
    }


}
