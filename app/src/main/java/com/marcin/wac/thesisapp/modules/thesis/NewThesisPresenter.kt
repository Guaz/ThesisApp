package com.marcin.wac.thesisapp.modules.thesis

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.body.ThesisBody
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import javax.inject.Inject

class NewThesisPresenter @Inject constructor(private val interactor: NewThesisInteractor,
                                             private val userSession: IUserSession
): BasePresenter<NewThesisView>(){

    override fun attachView(view: NewThesisView) {
        super.attachView(view)
        view.setOnButtonClickListener()
    }

    fun onPostButtonClick(title: String, description: String, language: String,
                          studentSkills: String, technologies: String, reviewerEmail: String){
        view?.showLoadingView()
        interactor.addThesis(ThesisBody(title, description, language, studentSkills, technologies, userSession.getEmail(), reviewerEmail), object: BaseCallback{
            override fun success() {
                view?.finishActivity()
                view?.hideLoadingView()
            }

            override fun error() {
                view?.showErrorToast()
                view?.hideLoadingView()
            }
        })
    }
}