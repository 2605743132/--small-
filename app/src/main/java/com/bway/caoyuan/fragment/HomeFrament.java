package com.bway.caoyuan.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.caoyuan.R;
import com.bway.caoyuan.activity.SearchActivity;
import com.bway.caoyuan.adapter.CommodityAdpater;
import com.bway.caoyuan.bean.BannrBean;
import com.bway.caoyuan.bean.HomeList;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.exception.ApiException;
import com.bway.caoyuan.presenter.BannerPresenter;
import com.bway.caoyuan.presenter.HomePresenter;
import com.bway.caoyuan.util.FrescoUtlis;
import com.bway.caoyuan.util.recyclerview.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFrament extends Fragment {


    @BindView(R.id.home_page_banner)
    MZBannerView mHomePageBanner;
    @BindView(R.id.home_page_img_menu)
    ImageButton mHomePageImgMenu;
    @BindView(R.id.home_page_edit_search)
    TextView mHomePageEditSearch;
    @BindView(R.id.home_page_img_search)
    ImageButton mHomePageImgSearch;
    @BindView(R.id.home_page_img_more_yellow)
    ImageView mHomePageImgMoreYellow;
    @BindView(R.id.home_page_hot_sale_goods)
    RecyclerView mHomePageHotSaleGoods;
    @BindView(R.id.home_page_img_more_purple)
    ImageView mHomePageImgMorePurple;
    @BindView(R.id.home_page_magic_goods)
    RecyclerView mHomePageMagicGoods;
    @BindView(R.id.home_page_img_more_pink)
    ImageView mHomePageImgMorePink;
    @BindView(R.id.home_page_q_life_goods)
    RecyclerView mHomePageQLifeGoods;
    private View view;
    private Unbinder unbinder;
    private BannerPresenter bannerPresenter;
    private HomePresenter homeListPresenter;
    private CommodityAdpater mHotAdapter;
    private CommodityAdpater mFashionAdapter;
    private CommodityAdpater mLifeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bannerPresenter = new BannerPresenter(new MyBanner());
        bannerPresenter.reqeust();
        mHotAdapter = new CommodityAdpater(getContext(),CommodityAdpater.HOT_TYPE);
        mFashionAdapter = new CommodityAdpater(getContext(),CommodityAdpater.FASHION_TYPE);
        mLifeAdapter = new CommodityAdpater(getContext(),CommodityAdpater.HOT_TYPE);

       mHomePageHotSaleGoods.setLayoutManager(new GridLayoutManager(getContext(),3));
        mHomePageMagicGoods.setLayoutManager(new GridLayoutManager(getContext(),1));
        mHomePageQLifeGoods.setLayoutManager(new GridLayoutManager(getContext(),2));

        int space = getResources().getDimensionPixelSize(R.dimen.margin_10dp);

        mHomePageHotSaleGoods.addItemDecoration(new SpacingItemDecoration(space));
        mHomePageMagicGoods.addItemDecoration(new SpacingItemDecoration(space));
        mHomePageQLifeGoods.addItemDecoration(new SpacingItemDecoration(space));

        mHomePageHotSaleGoods.setAdapter(mHotAdapter);
        mHomePageMagicGoods.setAdapter(mFashionAdapter);
        mHomePageQLifeGoods.setAdapter(mLifeAdapter);



        bannerPresenter = new BannerPresenter(new MyBanner());
        bannerPresenter.reqeust();

        homeListPresenter = new HomePresenter(new HomeCall());
        homeListPresenter.reqeust();

    }

    @Override
    public void onPause() {
        super.onPause();
        mHomePageBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mHomePageBanner.start();//开始轮播
    }




    private class MyBanner implements DataCall<Result<List<BannrBean>>> {
        @Override
        public void success(Result<List<BannrBean>> data) {
            if (data.getStatus().equals("0000")) {
                mHomePageBanner.setIndicatorVisible(false);
                mHomePageBanner.setPages(data.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
                    }
                });
                mHomePageBanner.start();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(),"失败了",Toast.LENGTH_SHORT).show();
        }


    }
    @OnClick({R.id.home_page_img_menu, R.id.home_page_edit_search, R.id.home_page_img_search, R.id.home_page_banner, R.id.home_page_img_more_yellow, R.id.home_page_hot_sale_goods, R.id.home_page_img_more_purple, R.id.home_page_magic_goods, R.id.home_page_img_more_pink, R.id.home_page_q_life_goods})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.home_page_img_menu:

                break;
            case R.id.home_page_edit_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

                break;
            case R.id.home_page_img_search:

                break;
            case R.id.home_page_banner:

                break;
            case R.id.home_page_img_more_yellow:


                break;
            case R.id.home_page_hot_sale_goods:

                break;
            case R.id.home_page_img_more_purple:

                break;
            case R.id.home_page_magic_goods:

                break;
            case R.id.home_page_img_more_pink:

                break;
            case R.id.home_page_q_life_goods:


                break;
        }
    }

    class HomeCall implements DataCall<Result<HomeList>>{

        @Override
        public void success(Result<HomeList> data) {
            if (data.getStatus().equals("0000")){
                //添加列表并刷新
                mHotAdapter.addAll(data.getResult().getRxxp().get(0).getCommodityList());
                mFashionAdapter.addAll(data.getResult().getMlss().get(0).getCommodityList());
                mLifeAdapter.addAll(data.getResult().getPzsh().get(0).getCommodityList());
                mHotAdapter.notifyDataSetChanged();
                mFashionAdapter.notifyDataSetChanged();
                mLifeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getActivity(),"失败了",Toast.LENGTH_SHORT).show();
        }
    }
    public static class BannerViewHolder implements MZViewHolder<BannrBean> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (SimpleDraweeView) view.findViewById(R.id.banner_pic);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannrBean data) {
            // 数据绑定

            mImageView.setImageURI(Uri.parse(data.getImageUrl()));
        }
    }



}





