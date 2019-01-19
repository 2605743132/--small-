package com.bway.caoyuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.FootBean;
import com.bway.caoyuan.bean.MyFootBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MyFootAdapter  extends RecyclerView.Adapter<MyFootAdapter.MyCircleViewHolder> {
    private Context context;
    private List<FootBean> list;

    public MyFootAdapter(Context context, List<FootBean> list) {
        this.context = context;
        this.list = list;
    }


    public void clearData() {
        list.clear();
    }
    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myfoot_re_item, parent, false);
        MyCircleViewHolder holder = new MyCircleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.mcirclepic.getLayoutParams();
        layoutParams.height = (int) ((400) + Math.random() * 450);

        holder.mcirclepic.setLayoutParams(layoutParams);
        holder.mcirclecount.setText(list.get(position).getCommodityName());
        holder.mcirclepic.setImageURI(list.get(position).getMasterPic());
        holder.mcirclemoney.setText(list.get(position).getPrice()+"");
        long createTime = list.get(position).getBrowseTime();
        Date date = new Date(createTime);
        holder.foot_data.setText(date + "");
        holder.foot_liulan.setText(list.get(position).getBrowseNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_data;
        private TextView foot_liulan;
        SimpleDraweeView mcirclepic;
        TextView mcirclecount, mcirclemoney;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            mcirclecount = itemView.findViewById(R.id.foot_count);
            mcirclepic = itemView.findViewById(R.id.foot_img);
            mcirclemoney = itemView.findViewById(R.id.foot_money);
            foot_liulan = itemView.findViewById(R.id.foot_liulan);
            foot_data = itemView.findViewById(R.id.myfootprint_item_date);
        }
    }

}