package com.vs.daggersample.di;

import com.vs.daggersample.DaggerSampleApplication;
import com.vs.daggersample.data.api.Api;
import com.vs.daggersample.di.contributors.ContributorsComponent;
import com.vs.daggersample.di.contributors.ContributorsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Valentyn on 13.04.2018.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DbModule.class})
public interface AppComponent {

    DaggerSampleApplication application();
    Api api();

    void inject(DaggerSampleApplication application);

    ContributorsComponent plus(ContributorsModule contributorsModule);

}
