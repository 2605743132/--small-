package com.bway.caoyuan.presenter;

import com.bway.caoyuan.base.BasePresenter;
import com.bway.caoyuan.bean.Result;
import com.bway.caoyuan.bean.SearchBean;
import com.bway.caoyuan.core.DataCall;
import com.bway.caoyuan.path.Util;
import com.bway.caoyuan.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;

public class SearchPresenter extends BasePresenter {

    private int page = 1;
    private boolean refresh = false;
    public SearchPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        Util iRequest = RetrofitUtil.instance().create(Util.class);
       refresh = (Boolean) args[0];
       if (refresh){
           page=1;
       }else {
           page++;
       }
        Observable<Result<List<SearchBean>>> search = iRequest.Search((String) args[1], page, 15);
        return search;
    }

}
