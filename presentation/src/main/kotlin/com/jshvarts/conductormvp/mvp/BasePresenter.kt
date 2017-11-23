package com.jshvarts.conductormvp.mvp

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MvpView> {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun destroy() {
        disposables.clear()
    }
}