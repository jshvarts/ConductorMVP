package com.jshvarts.conductormvp.colors

/**
 * MVP Contract for displaying list of colors.
 */
interface ColorsContract {
    interface View {
        fun onColorClicked(position: Int)
        fun displayUnableToLoadColorsError()
    }

    interface Presenter {
        fun loadColors()
        fun onUnableToLoadColors()
    }
}