package com.jshvarts.conductormvp.colors

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.bluelinelabs.conductor.RouterTransaction
import com.jshvarts.conductormvp.R
import com.jshvarts.conductormvp.app.ColorsApp
import com.jshvarts.conductormvp.colordetail.ColorDetailView
import com.jshvarts.conductormvp.mvp.BaseView
import javax.inject.Inject

class ColorsView : BaseView(), ColorsContract.View {

    @Inject
    lateinit var presenter: ColorsPresenter

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: ColorsAdapter

    override fun onAttach(view: View) {

        DaggerColorsComponent.builder()
                .appComponent(ColorsApp.component)
                .colorsModule(ColorsModule())
                .build()
                .inject(this)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        presenter.attachView(this)
        val colors = presenter.loadColors()

        recyclerViewAdapter = ColorsAdapter(colors)
        recyclerViewAdapter.onItemClick = { onItemClicked(colors[it])}
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }
    override fun onItemClicked(color: String) {
        val colorDetailView = ColorDetailView().apply {
            args.putString(ColorDetailView.EXTRA_ITEM, color)
        }
        router.pushController(RouterTransaction.with(colorDetailView))
    }

    override fun onUnableToLoadItems() {
        TODO("not implemented")
    }

    override fun getLayoutId() = R.layout.colors_recycler_view
}