package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.bean.FootBean;
import com.bway.caoyuan.bean.MyFootBean;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;

public class FootPresenter extends BasePresenter {
    private int page = 1;
    private boolean refresh = false;
    public FootPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
    page++;
        Observable<Result<List<FootBean>>> getmyfoot = iRequest.getmyfoot((long) args[0], (String) args[1],page, 5);
        return getmyfoot;
    }
}
