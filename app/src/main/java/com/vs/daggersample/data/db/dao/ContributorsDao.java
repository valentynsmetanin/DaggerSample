package com.vs.daggersample.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vs.daggersample.data.entity.Contributor;

import java.util.List;

/**
 * Created by Valentyn on 14.04.2018.
 */
@Dao
public interface ContributorsDao {

    @Query("SELECT * FROM contributor")
    List<Contributor> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Contributor> contributors);

}
