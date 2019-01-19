package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.core.DataCall;

import io.reactivex.Observable;

public class MyCirclePresenter extends BasePresenter {
    public MyCirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        return null;
    }
}
