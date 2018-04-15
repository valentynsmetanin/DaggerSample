package com.vs.daggersample.ui.contributordetails;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.vs.daggersample.data.entity.Contributor;

/**
 * Created by Valentyn on 15.04.2018.
 */
public interface ContributorDetailsView extends MvpView {

    void showContributor(Contributor contributor);

}
