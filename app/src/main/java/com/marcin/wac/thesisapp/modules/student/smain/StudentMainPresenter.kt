package com.marcin.wac.thesisapp.modules.student.smain

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse
import com.marcin.wac.thesisapp.utils.ParamCallback
import javax.inject.Inject

class StudentMainPresenter @Inject constructor(private val interactor: StudentMainInteractor): BasePresenter<StudentMainView>(){
    private var lastBackClick: Long = 0

    override fun attachView(view: StudentMainView) {
        super.attachView(view)
        view.showLoadingView()

        interactor.getThesisList(object : ParamCallback<GetThesisListResponse>{
            override fun success(response: GetThesisListResponse) {
                view.setThesisRecycler(response.thesisList)
            }

            override fun error() {
                super.error()

            }
        })

        interactor.getUserThesis(object : ParamCallback<ThesisModel>{
            override fun success(response: ThesisModel) {

            }

            override fun error() {
                super.error()
            }
        })
    }

    fun onThesisClicked(thesis: ThesisModel){
        interactor.getUserThesis(object : ParamCallback<ThesisModel>{
            override fun success(response: ThesisModel) {

            }

            override fun error() {
                super.error()

            }
        })
    }

    fun onBackPressed(){
        if (System.currentTimeMillis() - lastBackClick < 3000)
            view?.finishActivity()
        else {
            view?.showClickAgainToExitToast()
            lastBackClick = System.currentTimeMillis()
        }
    }
}