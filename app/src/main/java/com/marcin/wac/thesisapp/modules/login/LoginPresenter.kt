package com.marcin.wac.thesisapp.modules.login

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.body.LoginBody
import com.marcin.wac.thesisapp.utils.BaseCallback
import com.marcin.wac.thesisapp.utils.BooleanCallback
import javax.inject.Inject

class LoginPresenter @Inject constructor(private var interactor: LoginInteractor): BasePresenter<LoginView>() {

    override fun attachView(view: LoginView) {
        super.attachView(view)
        view.setRegisterClickListener()
        view.setLoginClickListener()
    }

    fun onLoginClick(email: String, password: String){
        view?.showLoadingLayout()
        interactor.login(LoginBody(email, password), object: BooleanCallback{
            override fun success(isStudent: Boolean) {
                if (isStudent) view?.startStudent() else view?.startLecturer()
                view?.hideLoadingLayout()
            }

            override fun error() {
                view?.hideLoadingLayout()
                view?.showErrorToast()
            }

        })
    }

    fun onRegisterClick(){
        view?.openRegisterActivity()
    }
}
