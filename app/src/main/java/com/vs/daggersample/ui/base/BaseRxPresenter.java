package com.vs.daggersample.ui.base;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Valentyn on 13.04.2018.
 */
public abstract class BaseRxPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    protected CompositeDisposable mDisposable;

    @Override
    public void attachView(V view) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance && mDisposable != null) {
            mDisposable.clear();
        }
        super.detachView(retainInstance);
    }

}
