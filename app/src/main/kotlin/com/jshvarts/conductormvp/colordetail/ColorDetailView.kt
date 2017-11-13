package com.jshvarts.conductormvp.colordetail

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.mvp.BaseView

class ColorDetailView : BaseView() {

    companion object {
        const val EXTRA_ITEM = "ColorDetailView.item"
    }

    @BindView(R.id.color_name)
    lateinit var colorName: TextView

    override fun getLayoutId() = R.layout.color_detail

    override fun onAttach(view: View) {
        colorName.text = args.get(EXTRA_ITEM).toString()
    }
}