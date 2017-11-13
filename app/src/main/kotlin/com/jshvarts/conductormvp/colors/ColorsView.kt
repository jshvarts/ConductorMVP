package com.jshvarts.conductormvp.colors

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.bluelinelabs.conductor.RouterTransaction
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.colordetail.ColorDetailView
import com.jshvarts.conductormvp.mvp.BaseView

class ColorsView : BaseView(), ColorsContract.View {

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: ColorsAdapter

    private val colors = listOf("red", "white", "blue", "green")

    override fun onAttach(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerViewAdapter = ColorsAdapter(colors)
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.onItemClick = { onColorClicked(it)}
    }

    override fun onColorClicked(position: Int) {
        val colorDetailView = ColorDetailView().apply {
            args.putString(ColorDetailView.EXTRA_ITEM, colors[position])
        }
        router.pushController(RouterTransaction.with(colorDetailView))
    }

    override fun displayUnableToLoadColorsError() {
        TODO("not implemented")
    }

    override fun getLayoutId() = R.layout.colors_recycler_view
}