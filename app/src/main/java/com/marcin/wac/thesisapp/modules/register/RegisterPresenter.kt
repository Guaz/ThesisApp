package com.marcin.wac.thesisapp.modules.register

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.body.RegisterBody
import com.marcin.wac.thesisapp.utils.BaseCallback
import javax.inject.Inject

class RegisterPresenter @Inject constructor(val interactor: RegisterInteractor): BasePresenter<RegisterView>(){

    override fun attachView(view: RegisterView) {
        super.attachView(view)
        view.setOnRegisterClickListener()
    }

    fun onRegisterClick(email: String, password: String, name: String, surname:String, role: String, university: String, department: String){
        interactor.register(RegisterBody(email, password, name, surname, "", role, university, department), object: BaseCallback{
            override fun success() {
                view?.showSuccessToast()
                view?.finishActivity()
            }

            override fun error() {
                view?.showFailureToast()
            }

        })
    }
}