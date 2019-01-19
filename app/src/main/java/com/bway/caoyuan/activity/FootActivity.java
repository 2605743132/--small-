package com.bway.caoyuan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bway.caoyuan.R;

import com.bway.caoyuan.adapter.MyFootAdapter;
import com.bway.caoyuan.bean.FootBean;
import com.bway.caoyuan.bean.MyFootBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.ResultBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.db.DaoMaster;
import com.bway.caoyuan.db.ResultBeanDao;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.FootPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootActivity extends AppCompatActivity {

    @BindView(R.id.my_foot_re)
    XRecyclerView mMyFootRe;
    private int page = 1;
    private FootPresenter footPresenter = new FootPresenter(new FootCalll());
    private MyFootAdapter adapter;
    private ResultBean LOGIN_USER;
    private List<FootBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_foot);
        ButterKnife.bind(this);


        ResultBeanDao userInfoDao = DaoMaster.newDevSession(this, ResultBeanDao.TABLENAME).getResultBeanDao();
        List<ResultBean> userInfos = userInfoDao.queryBuilder().where(ResultBeanDao.Properties.Status.eq(1)).list();

        if (userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }

        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        initView();


    }

    private void initView() {

            adapter = new  MyFootAdapter(this, list);;
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMyFootRe.setAdapter(adapter);
        mMyFootRe.setLayoutManager(manager);
        footPresenter.reqeust(LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());

        mMyFootRe.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                mMyFootRe.refreshComplete();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                footPresenter.reqeust(LOGIN_USER.getUserId(),LOGIN_USER.getSessionId());

                adapter.notifyDataSetChanged();
                mMyFootRe.loadMoreComplete();
            }
        });
    }


    public class FootCalll implements DataCall<Result<List<FootBean>>> {
        @Override
        public void success(Result<List<FootBean>> data) {
            if (data.getStatus().equals("0000")) {

                list.clear();

                list.addAll(data.getResult());


            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }

    }

}