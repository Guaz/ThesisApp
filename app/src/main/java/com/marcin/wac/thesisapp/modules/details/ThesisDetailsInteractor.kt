package com.marcin.wac.thesisapp.modules.details

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.UserModel
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import com.marcin.wac.thesisapp.utils.ParamCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class ThesisDetailsInteractor @Inject constructor(private val api: ThesisApi,
                                                  private val userSession: IUserSession) {
    private val disposable = CompositeDisposable()

    fun getStudentList(callback: ParamCallback<List<UserModel>>){
        disposable.add(api.getStudentOfDeparmentList(userSession.getDepartment())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.success(response.userList)

            }, {
                Log.d("TESTAGH", "err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }

    fun removeThesis(id: Long, callback: BaseCallback) {
        disposable.add(api.deleteThesis(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.success()
            }, {
                if (it is HttpException) {
                    when (it.code()) {
                        204 -> callback.success()
                        else -> callback.error()
                    }
                }
            }))
    }

    fun occupyThesis(id: Long, studentEmail: String, callback: BaseCallback){
        disposable.add(api.occupyThesisForStudent(id, studentEmail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.success()

            }, {
                Log.d("TESTAGH", "err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }

    fun reserveThesis(id: Long, studentEmail: String, callback: BaseCallback){
        disposable.add(api.reserveThesisForStudent(id, studentEmail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                callback.success()

            }, {
                Log.d("TESTAGH", "err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }
}