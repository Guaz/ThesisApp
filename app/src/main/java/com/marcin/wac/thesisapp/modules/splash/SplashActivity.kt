package com.marcin.wac.thesisapp.modules.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.modules.login.LoginActivity
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import javax.inject.Inject

interface SplashView {
    fun openLoginActivity()
    fun openMainActivity()

    fun showLoadingLayout()
    fun hideLoadingLayout()
}

class SplashActivity : BaseActivity(), SplashView{
    @Inject
    lateinit var  presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)

    }

    override fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun openMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}