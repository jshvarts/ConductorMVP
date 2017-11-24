package com.jshvarts.conductormvp.mvp

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseView> {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected var view: V? = null

    fun start(view: V) {
        this.view = view
    }

    fun stop() {
        this.view = null
    }

    fun destroy() {
        disposables.clear()
    }
}