package com.vs.daggersample.ui.contributordetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.vs.daggersample.GlideApp;
import com.vs.daggersample.R;
import com.vs.daggersample.data.entity.Contributor;
import com.vs.daggersample.utils.BlurTransformation;

/**
 * Created by Valentyn on 15.04.2018.
 */
public class ContributorDetailsActivity extends MvpActivity<ContributorDetailsView, ContributorDetailsPresenter>
        implements ContributorDetailsView {

    public static final String ARG_CONTRIBUTOR = "arg_contributor";

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView ivAvatar;
    private ImageView ivAvatarBackground;

    private Contributor mContributor;

    @NonNull
    @Override
    public ContributorDetailsPresenter createPresenter() {
        // TODO feature -> change to inject
        return new ContributorDetailsPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar = findViewById(R.id.toolbar);
        ivAvatar = findViewById(R.id.iv_avatar);
        ivAvatarBackground = findViewById(R.id.iv_avatar_background);

        initToolbar();

        if (getIntent().getExtras() != null) {
            mContributor = getIntent().getExtras().getParcelable(ARG_CONTRIBUTOR);
        }

        Log.e("TAG", "Contributor: " + mContributor);

        showContributor(mContributor);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        collapsingToolbarLayout.setTitleEnabled(false);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                int scroll = scrollRange + verticalOffset;
                if (scroll == 0 || scroll <= 46) {
                    Log.e("TAG", "Contributor name: " + mContributor.getName());
                    if (mContributor != null && mContributor.getLogin() != null) {
                        toolbar.setTitle(mContributor.getLogin());
                        ivAvatar.setTransitionName("");
                    }
                    isShow = true;
                } else if (isShow) {
                    toolbar.setTitle("");
                    ivAvatar.setTransitionName(getString(R.string.iv_avatar));
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void showContributor(Contributor contributor) {
        if (contributor == null) return;

        loadAvatar(contributor.getAvatarUrl());
    }

    private void loadAvatar(String url) {
        GlideApp.with(this)
                .load(url)
                .transform(new BlurTransformation(this))
                .into(ivAvatarBackground);

        GlideApp.with(this)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivAvatar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Activity activity, Contributor contributor) {
        Intent intent = new Intent(activity, ContributorDetailsActivity.class);
        intent.putExtra(ARG_CONTRIBUTOR, contributor);

        return intent;
    }


}
