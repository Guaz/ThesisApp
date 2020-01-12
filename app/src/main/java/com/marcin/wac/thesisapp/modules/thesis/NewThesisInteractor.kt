package com.marcin.wac.thesisapp.modules.thesis

import android.util.Log
import com.marcin.wac.thesisapp.infrastructure.api.ThesisApi
import com.marcin.wac.thesisapp.models.body.ThesisBody
import com.marcin.wac.thesisapp.persistence.IUserSession
import com.marcin.wac.thesisapp.utils.BaseCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewThesisInteractor @Inject constructor(private val api: ThesisApi,
                                              private val userSession: IUserSession) {
    private val disposable = CompositeDisposable()

    fun addThesis(thesisBody: ThesisBody, callback: BaseCallback) {
        disposable.add(api.postThesis(thesisBody)
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