package com.bway.caoyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.Commodity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class CommodityAdpater extends RecyclerView.Adapter<CommodityAdpater.MyHolder> {

    Context context;
    private List<Commodity> list = new ArrayList<>();
    public static final int HOT_TYPE = 0;
    public static final int FASHION_TYPE = 1;
    private int type;

    public CommodityAdpater(Context context, int type){
        this.context = context;
        this.type = type;
    }

    public void addAll(List<Commodity> list){
        if (list!=null){
            this.list.addAll(list);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (type == HOT_TYPE) {
            View view = View.inflate(viewGroup.getContext(), R.layout.home_mlss_layout, null);
            return new MyHolder(view);
        }else {
            View view = View.inflate(viewGroup.getContext(), R.layout.home_pinzhi_layout, null);
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        Commodity commodity = list.get(position);
        myHolder.image.setImageURI(Uri.parse(commodity.getMasterPic()));
        myHolder.price.setText(commodity.getPrice()+"");
        myHolder.text.setText(commodity.getCommodityName());

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取分类id
//                intent.putExtras("id",)

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView image;
        TextView text;
        TextView price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            price = itemView.findViewById(R.id.price);
        }
    }
}