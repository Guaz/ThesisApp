package com.marcin.wac.thesisapp.modules.student

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse
import com.marcin.wac.thesisapp.utils.ParamCallback
import javax.inject.Inject

class StudentMainPresenter @Inject constructor(private val interactor: StudentMainInteractor): BasePresenter<StudentMainView>(){
    private var lastBackClick: Long = 0
    private var userThesis: ThesisModel? = null

    override fun attachView(view: StudentMainView) {
        super.attachView(view)
        view.showLoadingView()

        interactor.getThesisList(object : ParamCallback<GetThesisListResponse>{
            override fun success(response: GetThesisListResponse) {
                view.setThesisRecycler(response.thesisList)
                view.hideLoadingView()
            }

            override fun error() {
                view?.showErrorToast()
                view?.hideLoadingView()

            }
        })

        interactor.getUserThesis(object : ParamCallback<ThesisModel>{
            override fun success(response: ThesisModel) {
                if (response.reserved)
                    view?.setUserThesisStatus("Status pracy dyplomowej: PRACA ZAREZERWOWANA\nKliknij aby zobaczyć detale pracy")
                else if (response.occupied)
                    view?.setUserThesisStatus("Status pracy dyplomowej: PRACA PRZYPISANA\nKliknij aby zobaczyć detale pracy")
                view?.setOnUserThesisClickListener()
            }

            override fun error() {
                view?.setUserThesisStatus("Status pracy dyplomowej: BRAK PRACY")
                view?.hideLoadingView()
            }
        })
    }

    fun onThesisClicked(thesis: ThesisModel){
        view?.startThesisDetailActivity(thesis)
    }

    fun onUserThesisClickListener(){
        if (userThesis != null)
            view?.startThesisDetailActivity(userThesis!!)
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