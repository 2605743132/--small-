package com.bway.caoyuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.ordel.DetailList;
import com.bway.caoyuan.bean.ordel.MyOrdelBean;
import com.bway.caoyuan.util.FrescoUtlis;
import com.bway.caoyuan.util.recyclerview.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class OrdelAllAdapter extends RecyclerView.Adapter<OrdelAllAdapter.MyHolder> {
    private Context context;
    private List<MyOrdelBean> list;
    private OrderitemAdapter adapter;

    public OrdelAllAdapter(Context context, List<MyOrdelBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.myordelallitem, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.grecy_order.setText(list.get(position).getOrderId());
        Log.i("ZZ", "onBindViewHolder: " + list.get(position).getOrderId());
        holder.grecy_money.setText(list.get(position).getPayAmount() + "");
        List<DetailList> detailList = list.get(position).getDetailList();
        int coun = 0;
        for (int k = 0; k < list.get(position).getDetailList().size(); k++) {
            coun += list.get(position).getDetailList().get(k).getCommodityCount();
        }
        adapter = new OrderitemAdapter(context);
        adapter.addAll(detailList);
        holder.grecy_crecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        int space = context.getResources().getDimensionPixelSize(R.dimen.margin_10dp);

        holder.grecy_crecy.addItemDecoration(new SpacingItemDecoration(space));

        holder.grecy_crecy.setAdapter(adapter);


        holder.grecy_count.setText("共" + coun + "件商品，需支付");

        if (list.get(position).getOrderStatus() == 1) {
            holder.grecy_topay.setText("待付款");
        } else if (list.get(position).getOrderStatus() == 2) {
            holder.grecy_topay.setText("待收货");
        } else if (list.get(position).getOrderStatus() == 3) {
            holder.grecy_topay.setText("待评价");
        } else if (list.get(position).getOrderStatus() == 9) {
            holder.grecy_topay.setText("已完成");
        }

        holder.grecy_topay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = holder.grecy_topay.getText();

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<MyOrdelBean> mlist) {
        list.addAll(mlist);

    }
    class MyHolder extends RecyclerView.ViewHolder {

        public TextView grecy_order;
        public RecyclerView grecy_crecy;
        public TextView grecy_count;
        public TextView grecy_money;
        public Button grecy_cancel;
        public Button grecy_topay;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.grecy_order = (TextView) itemView.findViewById(R.id.grecy_order);
            this.grecy_crecy = (RecyclerView) itemView.findViewById(R.id.grecy_crecy);
            this.grecy_count = (TextView) itemView.findViewById(R.id.grecy_count);
            this.grecy_money = (TextView) itemView.findViewById(R.id.grecy_money);
            this.grecy_topay = (Button) itemView.findViewById(R.id.grecy_topay);
        }


    }


}





