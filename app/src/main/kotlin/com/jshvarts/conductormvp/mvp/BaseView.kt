package com.jshvarts.conductormvp.mvp

import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Controller

abstract class BaseView : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @IdRes
    abstract protected fun getLayoutId(): Int
}