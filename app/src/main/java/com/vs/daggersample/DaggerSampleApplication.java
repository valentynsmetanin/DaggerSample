package com.vs.daggersample;

import android.app.Application;

import com.vs.daggersample.di.AppComponent;
import com.vs.daggersample.di.AppModule;
import com.vs.daggersample.di.DaggerAppComponent;

/**
 * Created by Valentyn on 13.04.2018.
 */
public class DaggerSampleApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
