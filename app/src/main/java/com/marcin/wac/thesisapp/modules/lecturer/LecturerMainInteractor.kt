package com.marcin.wac.thesisapp.modules.lecturer

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse
import com.marcin.wac.thesisapp.persistence.UserSession
import com.marcin.wac.thesisapp.utils.ParamCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class LecturerMainInteractor @Inject constructor(private val api: ThesisApi,
                                                 private val userSession: UserSession){
    private val disposable = CompositeDisposable()

    fun getThesisList(callback: ParamCallback<GetThesisListResponse>){
        disposable.add(api.getThesisListByPromoterEmail(userSession.getEmail())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                Log.d("TESTAGH", "2suc")


                callback.success(response)
            }, {
                if (it is HttpException){
                    when (it.code()){
                        404 -> callback.successEmpty()
                    }
                }


                Log.d("TESTAGH", "2err " + it.message)
//                if (logoutManager.isUnauthorized(it)) {
//                    logoutManager.logout()
//                }
            }
            ))
    }
}