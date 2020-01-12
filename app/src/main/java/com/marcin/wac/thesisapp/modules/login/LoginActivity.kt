package com.marcin.wac.thesisapp.modules.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.modules.lecturer.LecturerMainActivity
import com.marcin.wac.thesisapp.modules.register.RegisterActivity
import com.marcin.wac.thesisapp.modules.student.StudentMainActivity
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

interface LoginView{
    fun setLoginClickListener()
    fun setRegisterClickListener()
    fun startStudent()
    fun startLecturer()
    fun openRegisterActivity()
    fun showErrorToast()
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
    }

    override fun setLoginClickListener() {
        login_login_button.setOnClickListener {
            presenter.onLoginClick(login_email.text.toString(), login_password.text.toString())
        }
    }

    override fun setRegisterClickListener() {
        login_register_button.setOnClickListener {
            presenter.onRegisterClick()
        }
    }

    override fun startStudent() {
        startActivity(intentFor<StudentMainActivity>().clearTop().newTask())
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit)
        finish()
    }

    override fun startLecturer() {
        startActivity(intentFor<LecturerMainActivity>().clearTop().newTask())
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit)
        finish()
    }

    override fun openRegisterActivity() {
        startActivity(intentFor<RegisterActivity>())
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit)
    }

    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error_try_again_later), Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingLayout() {
        login_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingLayout() {
        login_loading.visibility = View.GONE
    }

}