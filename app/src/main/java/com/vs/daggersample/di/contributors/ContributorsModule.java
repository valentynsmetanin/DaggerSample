package com.vs.daggersample.di.contributors;

import com.vs.daggersample.data.api.Api;
import com.vs.daggersample.data.db.dao.ContributorsDao;
import com.vs.daggersample.ui.contributors.ContributorsPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Valentyn on 14.04.2018.
 */
@Module
public class ContributorsModule {

    @Provides
    @ForContributors
    @NotNull
    public ContributorsPresenter providesContributorsPresenter(Api api, ContributorsDao dao) {
        return new ContributorsPresenter(api, dao);
    }

}
