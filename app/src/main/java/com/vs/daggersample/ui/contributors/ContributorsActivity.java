package com.vs.daggersample.ui.contributors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;
import com.vs.daggersample.DaggerSampleApplication;
import com.vs.daggersample.R;
import com.vs.daggersample.data.entity.Contributor;
import com.vs.daggersample.di.contributors.ContributorsModule;
import com.vs.daggersample.ui.contributordetails.ContributorDetailsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

public class ContributorsActivity extends MvpViewStateActivity<ContributorsView,
        ContributorsPresenter, ContributorsViewState> implements ContributorsView {

    private Toolbar toolbar;
    private RecyclerView rvContributors;
    private ProgressBar progress;

    private ContributorsAdapter mAdapter;

    @Inject
    ContributorsPresenter mPresenter;

    @NonNull
    @Override
    public ContributorsPresenter createPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    public ContributorsViewState createViewState() {
        return new ContributorsViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        mPresenter.getContributors();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getApplication() instanceof DaggerSampleApplication) {
            DaggerSampleApplication app = (DaggerSampleApplication)getApplication();
            app.getAppComponent().plus(new ContributorsModule()).inject(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributors);

        toolbar = findViewById(R.id.toolbar);
        rvContributors = findViewById(R.id.rv_contributors);
        progress = findViewById(R.id.progress);

        toolbar.setTitle(R.string.contributors);
        setSupportActionBar(toolbar);

        initRecycler();
    }

    private void initRecycler() {
        rvContributors.setHasFixedSize(true);

        rvContributors.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvContributors.getContext(),
                LinearLayoutManager.VERTICAL);
        rvContributors.addItemDecoration(dividerItemDecoration);

        mAdapter = new ContributorsAdapter(this);
        mAdapter.setListener(new ContributorsAdapter.OnContributorClickListener() {
            @Override
            public void onContributorClick(@NotNull Contributor contributor, @NotNull ImageView ivAvatar) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(ContributorsActivity.this, ivAvatar, getString(R.string.iv_avatar));
                Intent intent = ContributorDetailsActivity
                        .newIntent(ContributorsActivity.this, contributor);

                startActivity(intent, options.toBundle());
            }
        });

        rvContributors.setAdapter(mAdapter);
    }
    @Override
    public void showContributors(ArrayList<Contributor> contributors) {
        viewState.contributors = contributors;
        mAdapter.setData(contributors);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

}
