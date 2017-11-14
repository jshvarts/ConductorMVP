package com.jshvarts.conductormvp.mvp

abstract class BasePresenter<out V>(protected val view: V) {
    /**
     * Contains common setup actions needed, if any, for all presenters.
     * Subclasses may override this.
     */
    open fun start() {
    }

    /**
     * Contains common cleanup actions needed, if any, for all presenters.
     * Subclasses may override this.
     */
    open fun stop() {
    }
}