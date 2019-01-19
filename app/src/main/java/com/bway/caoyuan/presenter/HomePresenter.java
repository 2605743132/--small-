package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.bean.HomeList;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;

public class HomePresenter extends BasePresenter {
    public HomePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
            return iRequest.commodityList();
    }
}
