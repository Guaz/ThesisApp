package com.marcin.wac.thesisapp.modules.details

import com.marcin.wac.thesisapp.infrastructure.mvp.BasePresenter
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.models.UserModel
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import com.marcin.wac.thesisapp.utils.ParamCallback
import javax.inject.Inject

class ThesisDetailsPresenter @Inject constructor(val interactor: ThesisDetailsInteractor,
                                                 val userSession: IUserSession): BasePresenter<ThesisDetailsView>(){

    private lateinit var thesis: ThesisModel
    private lateinit var studentList: List<UserModel>
    private lateinit var studentChoosen: UserModel

    fun init(thesis: ThesisModel?){
        if (thesis == null) {
            view?.finishActivity()
            return
        }

        this.thesis = thesis

        if (userSession.isStudent()) {
            view?.hideButtons()
            view?.showReserveButton()
            view?.setOnReserveButtonClickListener()
        } else {
            view?.showLoadingLayout()
            interactor.getStudentList(object: ParamCallback<List<UserModel>>{
                override fun success(response: List<UserModel>) {
                    studentList = response
                    view?.hideLoadingLayout()
                }

                override fun error() {
                    view?.showErrorToast()
                    view?.finishActivity()
                }
            })
            view?.setOnChangeStatusClickListener()
            view?.setOnDeleteClickListener()
            view?.setOnChangeStatusConfirmationClickListener()
            view?.setOnCheckBoxClickLogic()
            view?.setOnTextEnteredTextWatcher()
        }

        view?.setTitle(thesis.title)
        view?.setDescription(thesis.description)
        view?.setLanguage(thesis.language)
        view?.setStudentSkills(thesis.studentsSkills)
        view?.setTechnologies(thesis.technologies)
        view?.setPromoterEmail(thesis.promoterEmail)
        view?.setReviewerEmail(thesis.reviewerEmail)
        view?.setReserved(if (thesis.reserved) "Zarezerwowany" else "Brak rezerwacji")
        view?.setOccupied(if (thesis.reserved) "Zajęty" else "Wolny")

        if (thesis.studentEmail.isNullOrBlank())
            view?.hideStudentEmailLayout()
        else
            view?.setStudentEmail(thesis.studentEmail)
    }

    fun onChangeStatusClick(isChangeLayoutVisible: Boolean){
        if (isChangeLayoutVisible)
            view?.hideChangeLayout()
        else
            view?.showChangeLayout()
    }

    fun onDeleteClick(){
        view?.showRemoveDialog()
    }

    fun onDeleteDialogYesClicked(){
        view?.showLoadingLayout()
        interactor.removeThesis(thesis.thesisId, object: BaseCallback{
            override fun success() {
                view?.showThesisRemovedToast()
                view?.finishActivity()
                view?.hideLoadingLayout()
            }

            override fun error() {
                view?.showErrorToast()
                view?.hideLoadingLayout()
            }
        })
    }

    fun onSearchTextChanged(text: String){
        if (text.isBlank()){
            view?.hideStudentRecycler()
            return
        }
        view?.setStudentsListAdapter(filterStudents(text))
        view?.showStudentRecycler()

    }

    private fun filterStudents(text: String): ArrayList<UserModel> {
        val mFilteredList = ArrayList<UserModel>()
        val mFilteredPriorityList = ArrayList<UserModel>()

        studentList.forEach {
            if (it.surname.toLowerCase().contains(text.toLowerCase()) && it.surname.toLowerCase().startsWith(text)) {
                mFilteredPriorityList.add(it)
            } else if (it.surname.toLowerCase().contains(text.toLowerCase())) {
                mFilteredList.add(it)
            }
        }
        mFilteredPriorityList.addAll(mFilteredList)
        return mFilteredPriorityList
    }

    fun onStudentClick(student: UserModel){
        this.studentChoosen = student
        view?.showChoosenStudent("${student.name} ${student.surname} (${student.department})")
        view?.showChangeStatusConfirmationButton()
    }

    fun onChangeStatusConfirmationClick(isOccupy: Boolean){
        if (isOccupy)
            interactor.occupyThesis(thesis.thesisId, studentChoosen.email, object: BaseCallback{
                override fun success() {
                    view?.showStatusChangedToast()
                    view?.finishActivity()
                }

                override fun error() {
                    view?.showErrorToast()
                }
            })
        else
            interactor.reserveThesis(thesis.thesisId, studentChoosen.email, object: BaseCallback{
                override fun success() {
                    view?.showStatusChangedToast()
                    view?.finishActivity()
                }

                override fun error() {
                    view?.showErrorToast()
                }
            })
    }

    fun onReserveThesisClick(){
        view?.openMailApp(thesis.promoterEmail)
    }
}