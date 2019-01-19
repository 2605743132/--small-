package com.bway.caoyuan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.SearchBean;
import com.bway.caoyuan.util.FrescoUtlis;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    private Context context;
    private List<SearchBean> list = new ArrayList<>();
    private int NORMAL_VIEW=0;
    public SearchAdapter(Context context) {
        this.context = context;

    }

    public void addAll(List<SearchBean> data) {
                        if (data != null) {
                            list.addAll(data);
        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener= listener; }

    public void clearData() {
        list.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {





        FrescoUtlis.setTu(list.get(position).getMasterPic(), holder.image3);
        holder.text3.setText(list.get(position).getCommodityName());
        holder.price3.setText("￥" + list.get(position).getPrice() + "");

        if (getItemViewType(position) == NORMAL_VIEW) {
            if (onItemClickListener != null)
                holder.image3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                    }
                });
        }
}






    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image3;
        public TextView text3;
        public TextView price3;

        public ViewHolder(View itemView) {
            super(itemView);
            image3 = itemView.findViewById(R.id.image3);
            text3 = itemView.findViewById(R.id.text3);
            price3 = itemView.findViewById(R.id.price3);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
