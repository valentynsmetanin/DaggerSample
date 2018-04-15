package com.vs.daggersample.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vs.daggersample.BuildConfig;
import com.vs.daggersample.data.db.dao.ContributorsDao;
import com.vs.daggersample.data.entity.Contributor;

/**
 * Created by Valentyn on 14.04.2018.
 */
@Database(entities = {Contributor.class}, version = BuildConfig.DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContributorsDao contributorsDao();

}
