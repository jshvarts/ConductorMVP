package com.jshvarts.conductormvp.mvp

interface Presenter<in T : BaseView> {
    fun detachView()
    fun attachView(view: T)
}