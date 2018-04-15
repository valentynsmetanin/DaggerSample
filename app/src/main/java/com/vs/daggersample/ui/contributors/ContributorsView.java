package com.vs.daggersample.ui.contributors;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.vs.daggersample.data.entity.Contributor;

import java.util.ArrayList;

/**
 * Created by Valentyn on 13.04.2018.
 */
public interface ContributorsView extends MvpView {

    void showContributors(ArrayList<Contributor> contributors);
    void showProgress(boolean show);

}
