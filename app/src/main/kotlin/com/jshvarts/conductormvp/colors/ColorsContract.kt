package com.jshvarts.conductormvp.colors

import com.jshvarts.conductormvp.mvp.BasePresenter

/**
 * MVP Contract for displaying list of colors.
 */
interface ColorsContract {

    interface View {
        fun onItemClicked(item: String)
        fun onUnableToLoadItems()
    }

    interface Presenter : BasePresenter<ColorsView> {
        fun loadColors(): List<String>
    }
}