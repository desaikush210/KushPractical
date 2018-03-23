package com.kush.kushdesaipractical.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 3/21/2018.
 */

public class ApiHelper {

//    private static final String BASE_URL = "https://api.github.com/";
    private static final String BASE_URL = "http://api.giphy.com/";
    private OkHttpClient okHttpClient;
    private ApiHelper(Context context) {
        okHttpClient = OkHttpClientHelper.provideOkHttpClient(context);
    }
    public static ApiHelper getInstance(Context context) {
        return new ApiHelper(context);
    }
    private Retrofit provideRestAdapter() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public <S> S getService(Class<S> serviceClass) {
        return provideRestAdapter().create(serviceClass);
    }

    public static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

}
