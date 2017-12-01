package com.jshvarts.conductormvp.mvp

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseView> : MvpPresenter {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected var view: V? = null
        private set

    fun start(view: V) {
        this.view = view
    }

    override fun stop() {
        this.view = null
    }

    override fun destroy() {
        disposables.clear()
    }
}