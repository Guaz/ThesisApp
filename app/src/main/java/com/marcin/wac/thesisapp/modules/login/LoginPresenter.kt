package com.marcin.wac.thesisapp.modules.login

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.body.LoginBody
import com.marcin.wac.thesisapp.utils.BaseCallback
import com.marcin.wac.thesisapp.utils.BooleanCallback
import javax.inject.Inject

class LoginPresenter @Inject constructor(private var interactor: LoginInteractor): BasePresenter<LoginView>() {

    fun onButtonClick(email: String, password: String){
        interactor.login(LoginBody(email, password), object: BooleanCallback{
            override fun success(isStudent: Boolean) {
                if (isStudent) view?.startStudent() else view?.startLecturer()
            }

            override fun error() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    override fun detachView() {
        super.detachView()
//        interactor.clearNetworking()
    }
}
