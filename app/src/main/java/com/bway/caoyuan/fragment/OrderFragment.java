package com.bway.caoyuan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bway.caoyuan.R;
import com.bway.caoyuan.adapter.HomeFragmentpageAdapter;
import com.bway.caoyuan.order_child.Order_Comment_Fragment;
import com.bway.caoyuan.order_child.Order_Pay_Fragment;
import com.bway.caoyuan.order_child.Order_all_ListFragment;
import com.bway.caoyuan.order_child.Order_finish_Fragment;
import com.bway.caoyuan.order_child.Order_receiver_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderFragment extends Fragment {
    @BindView(R.id.rb_order_all_list)
    RadioButton mRbOrderAllList;
    @BindView(R.id.rb_order_pay)
    RadioButton mRbOrderPay;
    @BindView(R.id.rb_order_receiver)
    RadioButton mRbOrderReceiver;
    @BindView(R.id.rb_order_comment)
    RadioButton mRbOrderComment;
    @BindView(R.id.rb_order_finish)
    RadioButton mRbOrderFinish;
    @BindView(R.id.order_view_page)
    ViewPager mOrderViewPage;
    @BindView(R.id.odgroup)
    RadioGroup mOdgroup;
    private View view;
    private Unbinder unbinder;
    private List<Fragment> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add(new Order_all_ListFragment());
        list.add(new Order_Pay_Fragment());
        list.add(new Order_receiver_Fragment());
        list.add(new Order_Comment_Fragment());
        list.add(new Order_finish_Fragment());

        mOrderViewPage.setAdapter(new HomeFragmentpageAdapter(getActivity().getSupportFragmentManager(), list));
        mOrderViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override

            public void onPageSelected(int position) {


                mOdgroup.check(mOdgroup.getChildAt(position).getId());


            }

            @Override

            public void onPageScrollStateChanged(int state) {


            }

        });


    }

    @OnClick({R.id.rb_order_all_list, R.id.rb_order_pay, R.id.rb_order_receiver, R.id.rb_order_comment, R.id.rb_order_finish, R.id.order_view_page, R.id.odgroup})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb_order_all_list:
                mOrderViewPage.setCurrentItem(0);
                break;
            case R.id.rb_order_pay:
                mOrderViewPage.setCurrentItem(1);
                break;
            case R.id.rb_order_receiver:
                mOrderViewPage.setCurrentItem(2);
                break;
            case R.id.rb_order_comment:
                mOrderViewPage.setCurrentItem(3);
                break;
            case R.id.rb_order_finish:
                mOrderViewPage.setCurrentItem(4);
                break;
            case R.id.order_view_page:
                break;
            case R.id.odgroup:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
