package com.jshvarts.conductormvp.colors

import com.jshvarts.conductormvp.mvp.BasePresenter

class ColorsPresenter(view: ColorsView) : BasePresenter<ColorsView>(view), ColorsContract.Presenter {
    override fun loadColors() = listOf("red", "white", "blue", "green")
}