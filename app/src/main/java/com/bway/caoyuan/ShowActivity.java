package com.bway.caoyuan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bway.caoyuan.adapter.HomeFragmentpageAdapter;
import com.bway.caoyuan.fragment.CartFragment;
import com.bway.caoyuan.fragment.CircleFragment;
import com.bway.caoyuan.fragment.HomeFrament;
import com.bway.caoyuan.fragment.MyFragment;
import com.bway.caoyuan.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity {
private List<Fragment> list = new ArrayList<>();
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.rbHome)
    RadioButton mRbHome;
    @BindView(R.id.rbCatagory)
    RadioButton mRbCatagory;
    @BindView(R.id.rbShopcart)

    RadioButton mRbShopcart;


    @BindView(R.id.rbding)
    RadioButton mRbding;
    @BindView(R.id.rbMine)
    RadioButton mRbMine;
    @BindView(R.id.rgHome)
    RadioGroup mRgHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        initData();
    }


    void initData() {
        list.add(new HomeFrament());
        list.add(new CircleFragment());
        list.add(new CartFragment());

        list.add(new OrderFragment());
        list.add(new MyFragment());
        mVp.setAdapter(new HomeFragmentpageAdapter(getSupportFragmentManager(), list));
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override

            public void onPageSelected(int position) {


                mRgHome.check(mRgHome.getChildAt(position).getId());


            }

            @Override

            public void onPageScrollStateChanged(int state) {


            }

        });


    }



    @OnClick({R.id.vp, R.id.rbHome, R.id.rbCatagory, R.id.rbShopcart, R.id.rbding, R.id.rbMine, R.id.rgHome})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.vp:
                break;
            case R.id.rbHome:
                mVp.setCurrentItem(0);
                break;
            case R.id.rbCatagory:
                mVp.setCurrentItem(1);
                break;
            case R.id.rbShopcart:
                mVp.setCurrentItem(2);
                break;
            case R.id.rbding:
                mVp.setCurrentItem(3);
                break;
            case R.id.rbMine:
                mVp.setCurrentItem(4);
                break;

        }
    }
}
