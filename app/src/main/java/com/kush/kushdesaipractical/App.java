package com.kush.kushdesaipractical;

import android.app.Application;

import com.kush.kushdesaipractical.database.AppDatabase;

/**
 * Created by Administrator on 3/23/2018.
 */

public class App extends Application {

    private boolean appRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getInstance(this); //This will provide AppDatabase Instance
    }
}
