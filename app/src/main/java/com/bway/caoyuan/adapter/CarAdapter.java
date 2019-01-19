package com.bway.caoyuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.CarBean;
import com.bway.caoyuan.view.AddandSub;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyCarViewHolder> {

    //条目checkbox
    private OnShopCartClickListener listener;

    public void setOnShopCartClickListener(OnShopCartClickListener listener) {
        this.listener = listener;
    }

    public interface OnShopCartClickListener {
        void onShopCartClick(int position, boolean isChecked);
    }

    //全选框
    private OnAddClickListener addListener;

    public void setOnAddClickListener(OnAddClickListener listener) {
        this.addListener = listener;
    }

    public interface OnAddClickListener {
        void onAddClick();
    }
    //////
    private Context context;
    private List<CarBean> carlist;


    public CarAdapter(Context context, List<CarBean> carlist) {
        this.context = context;
        this.carlist = carlist;
    }

    @NonNull
    @Override
    public MyCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        MyCarViewHolder holder = new MyCarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCarViewHolder holder, final int position) {
        final CarBean resultBean = carlist.get(position);
        holder.mcarpic.setImageURI(carlist.get(position).getPic());
        holder.mcarcount.setText(carlist.get(position).getCommodityName());
        holder.mcarmoney.setText("￥" + resultBean.getPrice());

        //选择
        holder.mcarbox.setOnCheckedChangeListener(null);
        holder.mcarbox.setChecked(resultBean.isChecked());
        //单选框改变监听
        holder.mcarbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resultBean.setChecked(isChecked);
                if (listener!=null) {
                    listener.onShopCartClick(position, isChecked);
                }
            }
        });
        //商品数量
        holder.maddsum.setCurentCount(carlist.get(position).getCount());
        holder.maddsum.setOnNumChangedListener(new AddandSub.OnNumChangedListener() {
            @Override
            public void onNumChanged(View view, int curNum) {
                resultBean.setCount(curNum);
                if (addListener!=null){
                    addListener.onAddClick();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return carlist.size();
    }

   class MyCarViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mcarpic;
        TextView mcarcount;
        TextView mcarmoney;
        CheckBox mcarbox;
        AddandSub maddsum;

        public MyCarViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.car_box);
            mcarpic = itemView.findViewById(R.id.car_pic);
            mcarcount = itemView.findViewById(R.id.car_count);
            mcarmoney = itemView.findViewById(R.id.car_money);
            mcarbox = itemView.findViewById(R.id.car_box);
            maddsum = itemView.findViewById(R.id.addsum);
        }
    }
}