package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.SerializableBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;

public class MyOrdelPresenter extends BasePresenter {
    public MyOrdelPresenter(DataCall dataCall) {
        super(dataCall);
    }
    private String path = "2019011020513162044";
    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
        return iRequest.getmyordel((long) args[0],(String)args[1],(int)args[2],(int)args[3],(int)args[4]);
    }
}
