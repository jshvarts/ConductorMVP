package com.jshvarts.conductormvp.colors

/**
 * MVP Contract for displaying list of colors.
 */
interface ColorsContract {
    interface View {
        fun onColorClicked(position: Int)
        fun onUnableToLoadColors()
    }

    interface Presenter {
        fun loadColors(): List<String>
    }
}