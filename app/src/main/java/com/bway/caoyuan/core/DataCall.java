package com.bway.caoyuan.core;

import com.bway.caoyuan.exception.ApiException;

public interface DataCall<T> {

    void success(T data);

    void fail(ApiException e);
}