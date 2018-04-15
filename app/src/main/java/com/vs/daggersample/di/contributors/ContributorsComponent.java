package com.vs.daggersample.di.contributors;

import com.vs.daggersample.ui.contributors.ContributorsActivity;
import com.vs.daggersample.ui.contributors.ContributorsPresenter;

import dagger.Subcomponent;

/**
 * Created by Valentyn on 14.04.2018.
 */
@Subcomponent(modules = {ContributorsModule.class})
@ForContributors
public interface ContributorsComponent {

    ContributorsPresenter contributorsPresenter();

    void inject(ContributorsActivity activity);

}
