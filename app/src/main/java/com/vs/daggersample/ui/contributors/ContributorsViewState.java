package com.vs.daggersample.ui.contributors;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState;
import com.vs.daggersample.data.entity.Contributor;

import java.util.ArrayList;

/**
 * Created by Valentyn on 14.04.2018.
 */
public class ContributorsViewState implements RestorableViewState<ContributorsView> {

    public static final String ARG_CONTRIBUTORS = "arg_contributors";

    ArrayList<Contributor> contributors;

    @Override
    public void apply(ContributorsView view, boolean retained) {
        if (contributors != null) {
            view.showContributors(contributors);
        }
    }

    @Override
    public void saveInstanceState(@NonNull Bundle out) {
        out.putParcelableArrayList(ARG_CONTRIBUTORS, contributors);
    }

    @Override
    public RestorableViewState<ContributorsView> restoreInstanceState(Bundle in) {
        contributors = in.getParcelableArrayList(ARG_CONTRIBUTORS);
        return this;
    }

}
