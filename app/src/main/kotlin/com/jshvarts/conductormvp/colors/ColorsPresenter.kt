package com.jshvarts.conductormvp.colors

import timber.log.Timber

class ColorsPresenter : ColorsContract.Presenter {

    private lateinit var view: ColorsView

    override fun detachView() {
        Timber.d("BasePresenter::detachView")
    }

    override fun attachView(view: ColorsView) {
        Timber.d("BasePresenter::attachView")
        this.view = view
    }

    override fun loadColors() = listOf("red", "white", "blue", "green")
}