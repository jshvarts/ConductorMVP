package com.jshvarts.conductormvp.mvp

interface BasePresenter<in T : BaseView> {
    fun detachView()
    fun attachView(view: T)
}