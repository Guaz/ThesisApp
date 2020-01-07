package com.marcin.wac.thesisapp.modules.student.smain

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.ThesisModel
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse
import com.marcin.wac.thesisapp.persistence.UserSession
import com.marcin.wac.thesisapp.utils.ParamCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StudentMainInteractor @Inject constructor(private val api: ThesisApi,
                                                private val userSession: UserSession) {
    private val disposable = CompositeDisposable()

    fun getThesisList(callback: ParamCallback<GetThesisListResponse>){
        disposable.add(api.getThesisListByDepartment(userSession.getDepartment())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                Log.d("TESTAGH", "2suc")


                callback.success(response)
            }, {
                Log.d("TESTAGH", "2err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }

    fun getUserThesis(callback: ParamCallback<ThesisModel>){
        disposable.add(api.getThesisByStudentEmail(userSession.getEmail())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                Log.d("TESTAGH", "3suc")

                callback.success(response)
            }, {
                Log.d("TESTAGH", "3err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }
}