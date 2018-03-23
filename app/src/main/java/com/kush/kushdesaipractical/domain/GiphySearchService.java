package com.kush.kushdesaipractical.domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 3/21/2018.
 */

public interface GiphySearchService {

    @GET("v1/gifs/search")
    Observable<JsonObject> searchlist(@Query("q") String q, @Query("api_key") String api_key, @Query("limit") String limit);

}
