package com.bway.caoyuan.order_child;

import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import com.bway.caoyuan.R;
import com.bway.caoyuan.WDApplication;
import com.bway.caoyuan.adapter.OrdelAllAdapter;
import com.bway.caoyuan.bean.ordel.MyOrdelBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.MyOrdelPresenter;
import com.bway.caoyuan.util.recyclerview.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.ButterKnife;

import butterknife.Unbinder;


public class Order_all_ListFragment extends Fragment {

    @BindView(R.id.order_alllist_recy)
    RecyclerView orderAlllistRecy;
    Unbinder unbinder;
    private MyOrdelPresenter myOrdelPresenter = new MyOrdelPresenter(new OrdelCall());
    private ResultBean LOGIN_USER;
    private OrdelAllAdapter ordelAlladapter;
    private List<MyOrdelBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_all__list,
                container, false);

        ResultBeanDao userInfoDao = DaoMaster.newDevSession(WDApplication.getContext(), ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }


        unbinder = ButterKnife.bind(this, view);


        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myOrdelPresenter.reqeust(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId(),0,1,10);

        ordelAlladapter = new OrdelAllAdapter(getActivity(), list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(WDApplication.getContext());
        orderAlllistRecy.setLayoutManager(layoutManager);


        int space = getResources().getDimensionPixelSize(R.dimen.margin_10dp);

        orderAlllistRecy.addItemDecoration(new SpacingItemDecoration(space));
        orderAlllistRecy.setAdapter(ordelAlladapter);
    }

    private class OrdelCall implements DataCall<Result<List<MyOrdelBean>>> {
        @Override
        public void success(Result<List<MyOrdelBean>> data) {
            if (data.getStatus().equals("0000")) {
                List<MyOrdelBean> dataOrderList = data.getOrderList();
                list.clear();
                list.addAll(dataOrderList);
            }
            ordelAlladapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}





