package com.bway.caoyuan.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.adapter.CircleAdpater;
import com.bway.caoyuan.adapter.UserQuanZiAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleActivity extends AppCompatActivity {
    @BindView(R.id.user_del_quanzi_tong)
    ImageView userDelQuanziTong;
    @BindView(R.id.user_quanzi_recy)
    RecyclerView userQuanziRecy;
    @BindView(R.id.user_quanzi_radio)
    Button userQuanziRadio;
    private Unbinder mUnbinder;

    int da;
    private UserQuanZiAdapter mUserQuanZiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {


        mUserQuanZiAdapter = new UserQuanZiAdapter(this, null);   //创建适配器

        mUserQuanZiAdapter.setOnClick(new UserQuanZiAdapter.OnClick() {
            @Override
            public void success(int data) {
                da = data;
            }
        });

        //删除圈子
        userDelQuanziTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mUserPresenter1.delquanzi(da);
            }
        });

        //发布圈子

        userQuanziRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CircleActivity.this,AddCircleActivity.class);
                startActivity(intent);


            }
        });


    }


    float x, x2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x2 = event.getX();
            if (x2 - x > 200) {
                finish();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();


    }
}