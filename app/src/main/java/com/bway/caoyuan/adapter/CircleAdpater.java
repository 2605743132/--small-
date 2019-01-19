package com.bway.caoyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.bean.CircleBean;

import com.bway.caoyuan.presenter.PraisePresenter;
import com.bway.caoyuan.util.StringUtils;
import com.bway.caoyuan.util.recyclerview.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CircleAdpater extends RecyclerView.Adapter<CircleAdpater.MyHolder> {

    Context context;
    private List<CircleBean> list = new ArrayList<>();
    private PraisePresenter presenter ;
    public CircleAdpater(Context context){
        this.context = context;
    }

    public void addAll(List<CircleBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = View.inflate(viewGroup.getContext(), R.layout.circle_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int position) {
        final CircleBean circle = list.get(position);
        myHolder.avatar.setImageURI(Uri.parse(circle.getHeadPic()));
        myHolder.nickname.setText(circle.getNickName());
        myHolder.text.setText(circle.getContent());
      myHolder.text_zan.setText(circle.getGreatNum()+"");


        if (StringUtils.isEmpty(circle.getImage())){
            myHolder.gridView.setVisibility(View.GONE);
        }else{
            myHolder.gridView.setVisibility(View.VISIBLE);
            String[] images = circle.getImage().split(",");
            int colNum;//列数
            if (images.length == 1){
                colNum = 1;
            }else if (images.length == 2||images.length == 4){
                colNum = 2;
            }else {
                colNum = 3;
            }
            myHolder.imageAdapter.clear();//清空
//            for (int i = 0; i <imageCount ; i++) {
//                myHolder.imageAdapter.addAll(Arrays.asList(images));
//            }
            myHolder.imageAdapter.addAll(Arrays.asList(images));
            myHolder.gridLayoutManager.setSpanCount(colNum);//设置列数


            myHolder.imageAdapter.notifyDataSetChanged();
        }
        if (circle.getWhetherGreat() == 1){
            myHolder.img_zan.setImageResource(R.drawable.common_btn_prise_s);
        }else{
            myHolder.img_zan.setImageResource(R.drawable.common_btn_prise_n);
        }

        myHolder.text_zan.setText(circle.getGreatNum()+"");
        myHolder.img_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (greatListener!=null){
                    greatListener.great(position,circle);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public CircleBean getItem(int position) {

        return list.get(position);
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private ImageView img_zan;
        private TextView text_zan;
        SimpleDraweeView avatar;
        TextView nickname;
        TextView time;
        TextView text;
        RecyclerView gridView;
        GridLayoutManager gridLayoutManager;
        ImageAdapter imageAdapter;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            nickname = itemView.findViewById(R.id.nickname);
            time = itemView.findViewById(R.id.time);
            gridView = itemView.findViewById(R.id.grid_view);
            text_zan = itemView.findViewById(R.id.text_zan);
            img_zan = itemView.findViewById(R.id.img_zan);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.margin_10dp);;//图片间距
            gridLayoutManager = new GridLayoutManager(context,3);
            gridView.addItemDecoration(new SpacingItemDecoration(space));
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);
        }
    }
    private GreatListener greatListener;

    public void setGreatListener(GreatListener greatListener) {
        this.greatListener = greatListener;
    }

    public interface GreatListener{
        void great(int position,CircleBean circle);
    }

}

