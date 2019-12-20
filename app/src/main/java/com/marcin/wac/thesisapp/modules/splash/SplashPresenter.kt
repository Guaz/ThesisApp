package com.marcin.wac.thesisapp.modules.splash

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {

    override fun attachView(view: SplashView) {
        super.attachView(view)

        view?.openLoginActivity()
    }
}