package com.marcin.wac.thesisapp.modules.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.marcin.wac.thesisapp.R
import com.marcin.wac.thesisapp.infrastructure.di.DaggApp
import com.marcin.wac.thesisapp.utils.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject


interface RegisterView{
    fun setOnRegisterClickListener()
    fun showSuccessToast()
    fun showFailureToast()
    fun showLoadingLayout()
    fun hideLoadingLayout()
    fun finishActivity()
}

class RegisterActivity: BaseActivity(), RegisterView{
    @Inject
    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ((application as DaggApp)).appComponent.inject(this)
        presenter.attachView(this)
    }

    override fun setOnRegisterClickListener() {
        register_post_button.setOnClickListener {
            presenter.onRegisterClick(
                register_email.text.toString(),
                register_password.text.toString(),
                register_name.text.toString(),
                register_student_surname.text.toString(),
                register_role.text.toString(),
                register_reviewer_university.text.toString(),
                register_reviewer_department.text.toString()
            )
        }
    }

    override fun showSuccessToast() {
        Toast.makeText(this, "Konto zostało zarejestrowane, możesz się zalogować", Toast.LENGTH_SHORT).show()
    }

    override fun showFailureToast() {
        Toast.makeText(this, "Nie udało się stworzyć konta, adres email może być zajęty", Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingLayout() {
        register_loading.visibility = View.VISIBLE
    }

    override fun hideLoadingLayout() {
        register_loading.visibility = View.GONE
    }

    override fun finishActivity() {
        finish()
    }
}