package com.marcin.wac.thesisapp.modules.login

import android.content.Intent
import android.os.Bundle
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.modules.student.smain.StudentMainActivity
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

interface LoginView{
    fun setButtonListener()
    fun startStudent()
    fun startLecturer()
    fun showErrorLabel()
    fun showLoadingLayout()
    fun hideLoadingLayout()
}

class  LoginActivity : BaseActivity(), LoginView {
    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)
        setButtonListener()

    }

    override fun setButtonListener() {
        login_button.setOnClickListener {
            presenter.onButtonClick(login_email.text.toString(), login_password.text.toString())
        }
    }

    override fun startStudent() {
        startActivity(Intent(this, StudentMainActivity::class.java))
    }

    override fun startLecturer() {
        startActivity(Intent(this, StudentMainActivity::class.java))
    }

    override fun showErrorLabel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}