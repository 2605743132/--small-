package com.bway.caoyuan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bway.caoyuan.R;
import com.bway.caoyuan.activity.AddCircleActivity;
import com.bway.caoyuan.adapter.CircleAdpater;
import com.bway.caoyuan.bean.CircleBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.CirclePresenter;
import com.bway.caoyuan.presenter.PraisePresenter;
import com.bway.caoyuan.util.LogUtils;
import com.bway.caoyuan.util.UIUtils;
import com.bway.caoyuan.util.recyclerview.SpacingItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CircleFragment extends Fragment implements XRecyclerView.LoadingListener,CircleAdpater.GreatListener {


    public static boolean addCircle;
    XRecyclerView mCircleXResyleView;
    @BindView(R.id.add_circle)
    ImageView mAddCircle;
    //p层
    private CirclePresenter circlePresenter = new CirclePresenter(new CircleCall());
    private CircleAdpater mCircleAdapter;
    public ResultBean LOGIN_USER;



    private Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ResultBeanDao userInfoDao = DaoMaster.newDevSession(getContext(), ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }

        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        LogUtils.e(this.toString() + "页面加载使用：" + (System.currentTimeMillis() - time));
        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        return view;
    }

    private void initView(View view) {

        mCircleXResyleView = view.findViewById(R.id.circle_list);

        //适配器
        mCircleAdapter = new CircleAdpater(getContext());
        mCircleXResyleView.setAdapter(mCircleAdapter);

        //布局管理器
        mCircleXResyleView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        int space = getResources().getDimensionPixelSize(R.dimen.margin_20dp);

        mCircleXResyleView.addItemDecoration(new SpacingItemDecoration(space));

        //上拉和下拉的监听
        mCircleXResyleView.setLoadingListener(this);
        //刷新
        mCircleXResyleView.refresh();

    }


    @Override
    public void onRefresh() {
        if (circlePresenter.isRunning()) {
            mCircleXResyleView.refreshComplete();
            return;
        }
        circlePresenter.reqeust(true, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }


    @Override
    public void onLoadMore() {
        if (circlePresenter.isRunning()) {
            mCircleXResyleView.loadMoreComplete();
            return;
        }
        circlePresenter.reqeust(false, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    @OnClick(R.id.add_circle)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_circle:
                Intent intent  = new Intent(getActivity(),AddCircleActivity.class);
             startActivity(intent);
                break;
        }

    }

    @Override
    public void great(int position, CircleBean circle) {
        Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
        PraisePresenter presenter = new PraisePresenter(new PraiseCall());
       presenter.reqeust(LOGIN_USER.getUserId(),LOGIN_USER.getSessionId(),circle.getId(),position,circle);

    }


    class CircleCall implements DataCall<Result<List<CircleBean>>> {

        @Override
        public void success(Result<List<CircleBean>> data) {
            mCircleXResyleView.refreshComplete();
            mCircleXResyleView.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                //添加列表并刷新
                if (circlePresenter.getPage() == 1) {
                    mCircleAdapter.clear();
                }
                mCircleAdapter.addAll(data.getResult());
                mCircleAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            mCircleXResyleView.refreshComplete();
            mCircleXResyleView.loadMoreComplete();
        }
    }



    private class PraiseCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            int position = (int) data.getArgs()[3];

            CircleBean circle = (CircleBean) data.getArgs()[4];
            if (data.getStatus().equals("0000")){
                CircleBean nowCircle = mCircleAdapter.getItem(position);
                if (nowCircle.getId() == circle.getId()){
                    nowCircle.setGreatNum(nowCircle.getGreatNum()+1);
                    nowCircle.setWhetherGreat(1);
                    mCircleAdapter.notifyItemChanged(position+1,0);
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
