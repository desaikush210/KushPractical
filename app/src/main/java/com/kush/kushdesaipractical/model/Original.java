package com.kush.kushdesaipractical.model;

import retrofit2.http.GET;

/**
 * Created by Administrator on 3/23/2018.
 */

public class Original {

    String webp;
    String mp4;
    String url;
    int upvote;
    int downvote;

    public String getWebp() {
        return webp;
    }

    @GET("webp")
    public void setWebp(String webp) {
        this.webp = webp;
    }

    public String getMp4() {
        return mp4;
    }

    @GET("mp4")
    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getUrl() {
        return url;
    }

    @GET("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }
}
