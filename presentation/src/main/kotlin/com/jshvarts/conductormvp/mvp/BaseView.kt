package com.jshvarts.conductormvp.mvp

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Controller

abstract class BaseView : Controller() {

    // Inject dependencies once per life of Controller
    val inject by lazy { injectDependencies() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getLayoutId(), container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        setToolbarTitle()
    }

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        inject
    }

    protected fun View.hideKeyboard() {
        val inputMethodManager = applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    protected fun showMessage(@IdRes msgResId: Int) {
        Toast.makeText(this.applicationContext, msgResId, Toast.LENGTH_SHORT).show()
    }

    private fun setToolbarTitle() {
        // if the Activity happens to be non-AppCompatActivity or it does not have ActionBar, simply do not set the title
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            title = resources?.getString(getToolbarTitleId())
            setDisplayHomeAsUpEnabled(router.backstackSize > 1)
        }
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @StringRes
    protected abstract fun getToolbarTitleId(): Int

    protected abstract fun injectDependencies()
}