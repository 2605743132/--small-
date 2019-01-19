package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import io.reactivex.Observable;

public class SerializablePresenter extends BasePresenter {
    public SerializablePresenter(DataCall dataCall) {
        super(dataCall);

    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
        return iRequest.getSerializable((long) args[0] ,(String)args[1]);

    }

}
