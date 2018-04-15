package com.vs.daggersample.ui.contributors;

import com.vs.daggersample.data.api.Api;
import com.vs.daggersample.data.db.dao.ContributorsDao;
import com.vs.daggersample.data.entity.Contributor;
import com.vs.daggersample.ui.base.BaseRxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Valentyn on 13.04.2018.
 */
public class ContributorsPresenter extends BaseRxPresenter<ContributorsView> {

    private Api api;
    private ContributorsDao dao;

    @Inject
    public ContributorsPresenter(Api api, ContributorsDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public void getContributors() {
        getView().showProgress(true);

        Disposable d = api.repoContributors("square", "retrofit")
                .doOnNext(new Consumer<List<Contributor>>() {
                    @Override
                    public void accept(List<Contributor> contributors) {
                        dao.insert(contributors);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<Contributor>>() {
                    @Override
                    public void onNext(ArrayList<Contributor> contributors) {
                        getView().showProgress(false);
                        getView().showContributors(contributors);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showProgress(false);
                    }
                    @Override
                    public void onComplete() {}
                });

        mDisposable.add(d);

    }

}
