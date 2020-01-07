package com.marcin.wac.thesisapp.modules.lecturer

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse
import com.marcin.wac.thesisapp.utils.ParamCallback
import javax.inject.Inject

class LecturerMainPresenter @Inject constructor(private val interactor: LecturerMainInteractor): BasePresenter<LecturerMainView>(){

    override fun attachView(view: LecturerMainView) {
        super.attachView(view)

        view.showLoadingView()

        interactor.getThesisList(object: ParamCallback<GetThesisListResponse>{
            override fun success(response: GetThesisListResponse) {
                view.setThesisList(response.thesisList)
                view.hideLoadingView()
            }

            override fun successEmpty() {
                view.showNoThesisLayout()
                view.hideLoadingView()
            }

            override fun error() {
                super.error()
            }
        })
    }

    fun onThesisClicked(thesis: ThesisModel){

    }
}