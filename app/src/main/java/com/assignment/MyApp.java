package com.assignment;

import android.app.Application;
import android.content.Context;

import com.assignment.api.api.ApiRequestHelper;
import com.assignment.api.api.PrefManager;

public class MyApp extends Application {
    private PrefManager preferences;
    private ApiRequestHelper apiRequestHelper;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();

    }

    private void doInit() {
        this.preferences = new PrefManager(this);
        this.apiRequestHelper = apiRequestHelper.init(this);
    }

    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public synchronized PrefManager getPreferences() {
        return preferences;
    }
}
