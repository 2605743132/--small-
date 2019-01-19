package com.bway.caoyuan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bway.caoyuan.R;
import com.bway.caoyuan.adapter.CarAdapter;
import com.bway.caoyuan.bean.CarBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.CarPresenter;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CartFragment extends Fragment {


    @BindView(R.id.car_re)
    RecyclerView mCarRe;
    @BindView(R.id.box_all)
    CheckBox mBoxAll;
    @BindView(R.id.total_price)
    TextView mTotalPrice;
    private View view;
    private Unbinder unbinder;
    private List<CarBean> list = new ArrayList<>();
private CarPresenter carPresenter;
    private CarAdapter carAdapter;
    private ResultBean LOGIN_USER;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ResultBeanDao userInfoDao = DaoMaster.newDevSession(getContext(), ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }
        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        MobclickAgent.onProfileSignIn("购物车");

        carPresenter = new CarPresenter(new CarCall());
        carPresenter.reqeust(LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());
        carAdapter = new CarAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mCarRe.setAdapter(carAdapter);
        mCarRe.setLayoutManager(manager);



        //判断商品条目checkbox
        carAdapter.setOnShopCartClickListener(new CarAdapter.OnShopCartClickListener() {
            @Override
            public void onShopCartClick(int position, boolean isChecked) {
                //条目不选中,去哪选不选中
                if (!isChecked) {
                    mBoxAll.setChecked(false);
                } else {
                    boolean click = true;
                    //遍历判断条目是否都选中
                    for (CarBean resultBean : list) {
                        if (!resultBean.isChecked()) {//有不选中的
                            click = false;
                            break;//停止
                        }
                    }
                    mBoxAll.setChecked(click);//全选框不选中
                }
                getSum();//计算总价
            }
        });

        //全选
        carAdapter.setOnAddClickListener(new CarAdapter.OnAddClickListener() {
            @Override
            public void onAddClick() {
                getSum();//计算总价
            }
        });

    }





    @OnClick(R.id.box_all)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.box_all:

                MobclickAgent.onEvent(getActivity(),"hhh");
                boolean checked = mBoxAll.isChecked();//true
                for (CarBean resultBean : list) {
                    resultBean.setChecked(checked);//给集合中赋值true,条目checkbox选中
                }
                //刷新页面
                carAdapter.notifyDataSetChanged();
                getSum();//计算总价

                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(getActivity());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(getActivity());


    }
    public void getSum() {

        float sum = 0;
        for (CarBean resultBean : list) {
            if (resultBean.isChecked()) {
                sum += resultBean.getPrice() * resultBean.getCount();
            }
        }
       mTotalPrice.setText("" + sum);
    }
class CarCall implements DataCall<Result<List<CarBean>>> {
    @Override
    public void success(Result<List<CarBean>> data) {
        if (data.getStatus().equals("0000")) {
                list.clear();
                list.addAll(data.getResult());
        }
            carAdapter.notifyDataSetChanged();



    }

    @Override
    public void fail(ApiException e) {

    }
}

}

