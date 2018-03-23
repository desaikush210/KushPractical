package com.kush.kushdesaipractical.model;

import retrofit2.http.GET;

/**
 * Created by Administrator on 3/23/2018.
 */

public class Image480w_still {

    String url;

    public String getUrl() {
        return url;
    }

    @GET("url")
    public void setUrl(String url) {
        this.url = url;
    }
}
