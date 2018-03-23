package com.kush.kushdesaipractical.domain;

/**
 * Created by Administrator on 3/21/2018.
 */

public interface RxJava2ApiCallback<T> {
    void onSuccess(T t);
    void onFailed(Throwable throwable);
}