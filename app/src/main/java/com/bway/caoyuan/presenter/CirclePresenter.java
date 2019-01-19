package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.bean.CircleBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;

public class CirclePresenter extends BasePresenter {


    private int page = 1;

    public CirclePresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
        boolean refresh = (boolean) args[0];
        if (refresh) {
            page = 1;
        } else {
            page++;
        }
        return iRequest.findCircleList((long) args[1], (String) args[2], page, 20);
    }


    public int getPage() {
        return page;
    }
}
