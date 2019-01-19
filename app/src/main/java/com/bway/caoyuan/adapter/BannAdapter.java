package com.bway.caoyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.BannrBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

public class BannAdapter  extends PagerAdapter {
    private List<BannrBean> list;
    private Context context;

    public BannAdapter(List<BannrBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        context = container.getContext();
        ImageView imageView = new ImageView(context);

        Glide.with(context).load(list.get(position).getImageUrl()).into(imageView);

                container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}