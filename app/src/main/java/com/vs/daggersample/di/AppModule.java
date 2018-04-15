package com.vs.daggersample.di;

import com.vs.daggersample.DaggerSampleApplication;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Valentyn on 13.04.2018.
 */
@Module
public class AppModule {

    private DaggerSampleApplication app;

    public AppModule(DaggerSampleApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @NotNull
    public DaggerSampleApplication providesApplication() {
        return app;
    }

}
