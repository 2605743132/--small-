package com.bway.caoyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bway.caoyuan.R;
import com.bway.caoyuan.activity.details.DetailsActivity;
import com.bway.caoyuan.adapter.SearchAdapter;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.SearchBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.SearchPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.img_menu)
    ImageButton mImgMenu;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.img_search)
    ImageButton mImgSearch;
    @BindView(R.id.search_re)
    XRecyclerView mSearchRe;
    @BindView(R.id.linse)
    LinearLayout mLinse;
    @BindView(R.id.cangl)
    LinearLayout mCangl;
    private SearchPresenter searchPresenter = new SearchPresenter(new SearchCall());
    private SearchAdapter adapter;
    private List<SearchBean> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchRe.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SearchAdapter(getBaseContext());

        mSearchRe.setAdapter(adapter);
        adapter.clearData();

        initDate();

        initShow();
    }

    private void initShow() {
        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

    }

    private void initDate() {

        mSearchRe.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (searchPresenter.isRunning()) {
                    mSearchRe.refreshComplete();
                    mSearchRe.loadMoreComplete();
                    return;
                }
                adapter.clearData();
                String keyWord = mEditSearch.getText().toString();
                searchPresenter.reqeust(true, keyWord);

            }


            @Override
            public void onLoadMore() {
                if (searchPresenter.isRunning()) {
                    mSearchRe.loadMoreComplete();
                    return;
                }
                String keyWord = mEditSearch.getText().toString();
                searchPresenter.reqeust(false, keyWord);
            }
        });

    }

    @OnClick({R.id.img_menu, R.id.edit_search, R.id.img_search})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.img_menu:

                break;
            case R.id.edit_search:
                break;
            case R.id.img_search:
                String keyWord = mEditSearch.getText().toString();
                searchPresenter.reqeust(true, keyWord);
                adapter.clearData();
                break;

        }

    }


    private class SearchCall implements DataCall<Result<List<SearchBean>>> {
        @Override
        public void success(Result<List<SearchBean>> data) {
            mSearchRe.refreshComplete();
            mSearchRe.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
               if (data.getResult()!=null) {
                   List<SearchBean> keyWordBeanList = data.getResult();
                   adapter.addAll(keyWordBeanList);

                   adapter.notifyDataSetChanged();
               }else{
                   Toast.makeText(SearchActivity.this,"qqq",Toast.LENGTH_SHORT).show();
                   mCangl.setVisibility(View.GONE);
                   mLinse.setVisibility(View.VISIBLE);

               }

            }


        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }
}
