package com.marcin.wac.thesisapp.infrastructure.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V> {

    private val compositeDisposable = CompositeDisposable()
    var view: V? = null

    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun isViewAttached() = view != null
}