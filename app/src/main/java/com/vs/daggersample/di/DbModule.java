package com.vs.daggersample.di;

import android.arch.persistence.room.Room;

import com.vs.daggersample.BuildConfig;
import com.vs.daggersample.DaggerSampleApplication;
import com.vs.daggersample.data.db.AppDatabase;
import com.vs.daggersample.data.db.dao.ContributorsDao;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Valentyn on 13.04.2018.
 */
@Module
public class DbModule {

    @Provides
    @Singleton
    @NotNull
    AppDatabase providesAppDatabase(DaggerSampleApplication application) {
        return Room.databaseBuilder(application, AppDatabase.class, BuildConfig.DB_NAME)
                .build();
    }

    @Provides
    @Singleton
    @NotNull
    ContributorsDao providesContributorsDao(AppDatabase database) {
        return database.contributorsDao();
    }

}
