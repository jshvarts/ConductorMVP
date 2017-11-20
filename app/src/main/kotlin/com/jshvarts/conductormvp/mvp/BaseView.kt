package com.jshvarts.conductormvp.mvp

import android.content.Context
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

    protected fun View.hideKeyboard() {
        val inputMethodManager = applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    protected fun showMessage(@IdRes msgResId: Int) {
        Toast.makeText(this.applicationContext, msgResId, Toast.LENGTH_SHORT).show()
    }
}