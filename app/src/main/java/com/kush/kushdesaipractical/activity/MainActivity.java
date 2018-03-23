package com.kush.kushdesaipractical.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kush.kushdesaipractical.R;
import com.kush.kushdesaipractical.adapter.GiphyVideoAdapter;
import com.kush.kushdesaipractical.domain.ApiHelper;
import com.kush.kushdesaipractical.domain.GiphySearchService;
import com.kush.kushdesaipractical.domain.RxJava2ApiCallHelper;
import com.kush.kushdesaipractical.domain.RxJava2ApiCallback;
import com.kush.kushdesaipractical.model.GiphyvideoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edttxFind) EditText edttxFind;
    @BindView(R.id.imgbtnFind) ImageButton imgbtnFind;
    @BindView(R.id.videoRecyclerview) RecyclerView videoRecyclerview;

    GiphyVideoAdapter giphyVideoAdapter;

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCompositeDisposable = new CompositeDisposable();

        initView();

        imgbtnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttxFind.getText().length()>0){
                    callJSONApi(edttxFind.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Please enter input to find", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        videoRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        giphyVideoAdapter = new GiphyVideoAdapter(this);
        videoRecyclerview.setAdapter(giphyVideoAdapter);
    }

    private void callJSONApi(String find) {
        // check network is available or not if available then call api
        // otherwise show alert dialog
        if (!ApiHelper.isNetworkConnected(this)) {
            return;
        }

        // observable object prepared and returned from getService method of api helper class
        // getGitHubUser method return observable object
        Observable<JsonObject> observable = ApiHelper
                .getInstance(this)
                .getService(GiphySearchService.class)
                .searchlist(find,"DmJsRxvPZk8A76Uddm1kTxg1Gnm8vM2r","10");
//                .getGitHubUser();
        Disposable disposable = RxJava2ApiCallHelper.call(observable, new RxJava2ApiCallback<JsonObject>() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                Log.d("TAG", "onSuccess() called with: jsonArray = [" + jsonObject + "]");
                if (jsonObject != null) {
                    JsonArray jsonArray = jsonObject.getAsJsonArray("data");

                    ArrayList<GiphyvideoModel> giphyvideoArrayList = new ArrayList<>();
                    for(int i = 0;i<jsonArray.size();i++){
                        JsonElement videoJsonObject  = jsonArray.get(i).getAsJsonObject().get("images");

                        GiphyvideoModel gitHubUsers = new Gson().fromJson(videoJsonObject, GiphyvideoModel.class);
                        giphyvideoArrayList.add(gitHubUsers);
                    }
                    Log.d("TAG", giphyvideoArrayList.toString());
                    giphyVideoAdapter.setData(giphyvideoArrayList);
                }
            }
            @Override
            public void onFailed(Throwable throwable) {
                Log.d("TAG", "onFailed() called with: throwable = [" + throwable + "]");
                throwable.printStackTrace();
            }
        });
        mCompositeDisposable.add(disposable);
    }
}
