package com.bway.caoyuan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.activity.CircleActivity;
import com.bway.caoyuan.bean.UserQuanZi;
import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserQuanZiAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<UserQuanZi> mList;

    public UserQuanZiAdapter(Context context, List<UserQuanZi> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<UserQuanZi> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_quanzi_adapter, viewGroup, false);
        UserQuanZiViewHolder userQuanZiViewHolder =new UserQuanZiViewHolder(view);
        return userQuanZiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof UserQuanZiViewHolder) {
            ((UserQuanZiViewHolder) viewHolder).mNum.setText(""+mList.get(i).getGreatNum());
            long createTime = mList.get(i).getCreateTime();
            Date date =new Date(createTime);
            ((UserQuanZiViewHolder) viewHolder).mDate.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

            String image = mList.get(i).getImage();
            ((UserQuanZiViewHolder) viewHolder).mImage.setImageURI(Uri.parse(image));
            ((UserQuanZiViewHolder) viewHolder).mContent.setText(mList.get(i).getContent());

            ((UserQuanZiViewHolder) viewHolder).mRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.success(mList.get(i).getId());
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }



    class UserQuanZiViewHolder  extends RecyclerView.ViewHolder {

        private final TextView mContent;
        private final TextView mDate;
        private final TextView mNum;
        private final SimpleDraweeView mImage;
        private final RadioButton mRadio;

        public UserQuanZiViewHolder(@NonNull View itemView) {
            super(itemView);
            mRadio = itemView.findViewById(R.id.user_quanzi_adapter_radiobutton);
            mContent = itemView.findViewById(R.id.user_quanzi_adapter_content);
            mDate = itemView.findViewById(R.id.user_quanzi_adapter_date);
            mNum = itemView.findViewById(R.id.user_quanzi_adapter_num);
            mImage = itemView.findViewById(R.id.user_quanzi_adapter_image);

        }
    }

    private OnClick mOnClick;

    public interface OnClick{
        void success(int data);
    }
    public void setOnClick(OnClick mOnClick){
        this.mOnClick=mOnClick;
    }

}
