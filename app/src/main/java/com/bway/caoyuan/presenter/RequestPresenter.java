package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import io.reactivex.Observable;

public class RequestPresenter extends BasePresenter {
    public RequestPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
        return iRequest.register((String)args[0],(String)args[1]);
    }
}
