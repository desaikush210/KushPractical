package com.kush.kushdesaipractical.model;

import retrofit2.http.GET;

/**
 * Created by Administrator on 3/22/2018.
 */

public class GiphyvideoModel {


    Original original;
    Image480w_still image480w_still;

    public Image480w_still get480w_still() {
        return image480w_still;
    }

    @GET("480w_still")
    public void set480w_still(Image480w_still image) {
        this.image480w_still = image;
    }

    public Original getOriginal() {
        return original;
    }

    @GET("original")
    public void setOriginal(Original original) {
        this.original = original;
    }
}
